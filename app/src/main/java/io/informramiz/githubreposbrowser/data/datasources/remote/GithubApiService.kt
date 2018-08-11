package io.informramiz.githubreposbrowser.data.datasources.remote

import android.arch.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Ramiz Raja on 07/08/2018.
 */

interface GithubApiService {
    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String): LiveData<ApiResponse<ReposSearchResponse>>
}