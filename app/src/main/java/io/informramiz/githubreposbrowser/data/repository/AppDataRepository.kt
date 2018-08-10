package io.informramiz.githubreposbrowser.data.repository

import io.informramiz.githubreposbrowser.common.AppExecutors
import io.informramiz.githubreposbrowser.data.datasources.local.LocalDataSource
import io.informramiz.githubreposbrowser.data.datasources.remote.RemoteDataSource
import javax.inject.Inject


/**
 * Created by Ramiz Raja on 10/08/2018.
 */
open class AppDataRepository @Inject constructor(
        appExecutors: AppExecutors,
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource) : DataRepository {

}