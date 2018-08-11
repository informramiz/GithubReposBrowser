package io.informramiz.githubreposbrowser.data.repository

import android.arch.lifecycle.LiveData
import io.informramiz.githubreposbrowser.common.AppExecutors
import io.informramiz.githubreposbrowser.data.datasources.local.LocalDataSource
import io.informramiz.githubreposbrowser.data.datasources.remote.*
import io.informramiz.githubreposbrowser.data.models.Repo
import io.informramiz.githubreposbrowser.data.utils.AbsentLiveData
import io.informramiz.githubreposbrowser.data.utils.NetworkBoundResource
import io.informramiz.githubreposbrowser.data.utils.Resource
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Ramiz Raja on 10/08/2018.
 */
open class AppDataRepository @Inject constructor(
        val appExecutors: AppExecutors,
        val localDataSource: LocalDataSource,
        val remoteDataSource: RemoteDataSource,
        val githubApiService: GithubApiService) : DataRepository {
    override fun search(query: String): LiveData<Resource<List<Repo>>> {
        return object : NetworkBoundResource<List<Repo>, ReposSearchResponse>(appExecutors) {
            override fun saveCallResult(item: ReposSearchResponse) {
                Timber.i("Items loaded are $item")
            }

            override fun shouldFetchDataFromNetwork(data: List<Repo>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Repo>> {
                return AbsentLiveData.create()
            }

            override fun createCall(): LiveData<ApiResponse<ReposSearchResponse>> {
                return githubApiService.searchRepos(query)
            }

            override fun processResponse(response: ApiSuccessResponse<ReposSearchResponse>): ReposSearchResponse {
                val body = response.body
                body.nextPage = response.nextPage
                return body
            }

        }.asLiveData()
    }
}