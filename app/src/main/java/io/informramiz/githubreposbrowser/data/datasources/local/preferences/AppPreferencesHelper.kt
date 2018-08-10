package io.informramiz.githubreposbrowser.data.datasources.local.preferences

import android.content.Context
import android.content.SharedPreferences
import io.informramiz.githubreposbrowser.di.qualifiers.ApplicationContext
import javax.inject.Inject


/**
 * Created by Ramiz Raja on 10/08/2018.
 */
open class AppPreferencesHelper @Inject constructor(
        @ApplicationContext context: Context,
        sharedPreferences: SharedPreferences) : PreferencesHelper {

}