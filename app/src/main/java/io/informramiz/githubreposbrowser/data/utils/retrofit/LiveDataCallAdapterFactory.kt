package io.informramiz.githubreposbrowser.data.utils.retrofit

import android.arch.lifecycle.LiveData
import io.informramiz.githubreposbrowser.data.datasources.remote.ApiResponse
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/**
 * Created by Ramiz Raja on 12/08/2018.
 */
class LiveDataCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
            returnTye: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
            ): CallAdapter<*, *>? {
        //the upper most return type must of type LiveData (e.g. LiveData<ApiResponse<T>>)
        if (getRawType(returnTye) != LiveData::class.java) {
            //upper bound type is not of type LiveData, so invalid return type
            return null
        }

        //now check the remaining inner types (e.g. ApiResponse<T>)
        //first let's get the upper bound type (ApiResponse) of the main type (LiveData)
        val observableType = getParameterUpperBound(0, returnTye as ParameterizedType)
        //get class value of the observable type (e.g raw type of ApiResponse is ApiResponse::class.java
        val rawObservableType = getRawType(observableType)

        //verify that rawObservable type is ApiResponse::class.java
        if (rawObservableType != ApiResponse::class.java) {
            throw IllegalArgumentException("Call return type must be of type ApiResponse")
        }

        //also verify that observable type (ApiResponse) is also parametrized (e.g. ApiResponse<T>)
        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("Call return type (ApiResponse) must be parameterized")
        }

        //now get the upper most type in type nesting (e.g. LiveData<ApiResponse<T>> -> T. So T is
        //upper/inner most type in main return type)
        val bodyType = getParameterUpperBound(0, observableType)
        return LiveDataCallAdapter<Any>(bodyType)
    }
}