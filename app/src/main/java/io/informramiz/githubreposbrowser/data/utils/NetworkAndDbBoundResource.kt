package io.informramiz.githubreposbrowser.data.utils

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import io.informramiz.githubreposbrowser.common.AppExecutors
import io.informramiz.githubreposbrowser.data.datasources.remote.ApiEmptyResponse
import io.informramiz.githubreposbrowser.data.datasources.remote.ApiErrorResponse
import io.informramiz.githubreposbrowser.data.datasources.remote.ApiResponse
import io.informramiz.githubreposbrowser.data.datasources.remote.ApiSuccessResponse


/**
 * Created by Ramiz Raja on 10/08/2018.
 * A generic class to hold resource backed by both local database and network
 */
abstract class NetworkAndDbBoundResource<DesiredResultType, ApiResponseType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val resultMediatorLiveData = MediatorLiveData<Resource<DesiredResultType>>()

    init {
        //notify immediately that data is loading
        resultMediatorLiveData.value = Resource.loading(null)

        //load data from db first
        @Suppress("LeakingThis")
        val dbResultLiveData = loadFromDb()
        resultMediatorLiveData.addSource(dbResultLiveData) { data ->
            resultMediatorLiveData.removeSource(dbResultLiveData)

            if (shouldFetchDataFromNetwork(data)) {
                fetchFromNetwork(dbResultLiveData)
            } else {
                resultMediatorLiveData.addSource(dbResultLiveData) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    private fun fetchFromNetwork(dbResultLiveData: LiveData<DesiredResultType>) {
        //notify that app is loading data, also pass the current db data (if any)
        resultMediatorLiveData.addSource(dbResultLiveData) { data ->
            setValue(Resource.loading(data))
        }

        //now let's make and wait for network call to finish
        val apiResponse = createCall()
        resultMediatorLiveData.addSource(apiResponse) { callResponse ->
            //remove all current data sources (api + db)
            resultMediatorLiveData.removeSource(dbResultLiveData)
            resultMediatorLiveData.removeSource(apiResponse)
            //handle the Api call response and set value of ResultMediatorLiveData accordingly
            handleApiCallResponse(callResponse)
        }
    }

    private fun handleApiCallResponse(apiCallResponse: ApiResponse<ApiResponseType>?) {
        when(apiCallResponse) {
            is ApiSuccessResponse -> {
                //save the result of API call response
                appExecutors.diskIO.execute {
                    //save data to db
                    saveCallResult(processResponse(apiCallResponse))
                    //now notify the observers with new fresh data saved in
                    //db. NOTE, this notification should be done on main thread
                    appExecutors.mainThread.execute {
                        resultMediatorLiveData.addSource(loadFromDb()) { newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }
            }
            is ApiEmptyResponse -> {
                //we don't have new data, notify with whatever we already have in db
                appExecutors.mainThread.execute {
                    resultMediatorLiveData.addSource(loadFromDb()) { newData ->
                        setValue(Resource.success(newData))
                    }
                }
            }
            is ApiErrorResponse -> {
                onFetchFailed()
                //notify with old db data already loaded and an error msg
                appExecutors.mainThread.execute {
                    resultMediatorLiveData.addSource(loadFromDb()) { newData ->
                        setValue(Resource.error(apiCallResponse.errorMessage, newData))
                    }
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<DesiredResultType>) {
        if (resultMediatorLiveData.value != newValue) {
            resultMediatorLiveData.value = newValue
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = resultMediatorLiveData as LiveData<Resource<DesiredResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<ApiResponseType>): ApiResponseType {
        return response.body
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: ApiResponseType)

    @MainThread
    protected abstract fun shouldFetchDataFromNetwork(data: DesiredResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<DesiredResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<ApiResponseType>>
}