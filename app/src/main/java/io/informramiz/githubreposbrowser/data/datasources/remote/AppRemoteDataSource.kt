package io.informramiz.githubreposbrowser.data.datasources.remote

import io.informramiz.githubreposbrowser.common.AppExecutors
import javax.inject.Inject


/**
 * Created by Ramiz Raja on 10/08/2018.
 */
open class AppRemoteDataSource @Inject constructor(
        private val githubApiService: GithubApiService,
        private val appExecutors: AppExecutors) : RemoteDataSource {

}