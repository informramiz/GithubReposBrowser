package io.informramiz.githubreposbrowser.data.datasources.local

import io.informramiz.githubreposbrowser.common.AppExecutors
import io.informramiz.githubreposbrowser.data.datasources.local.preferences.PreferencesHelper
import javax.inject.Inject


/**
 * Created by Ramiz Raja on 10/08/2018.
 */
open class AppLocalDataSource @Inject constructor(
        preferencesHelper: PreferencesHelper,
        appExecutors: AppExecutors) : LocalDataSource {

}