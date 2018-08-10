package io.informramiz.githubreposbrowser

import android.app.Activity
import android.app.Application
import android.app.IntentService
import android.app.Service
import android.content.BroadcastReceiver
import dagger.android.*
import io.informramiz.githubreposbrowser.di.AppInjector
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Ramiz Raja on 07/08/2018.
 */

class GithubReposBrowserApplication : Application(), HasActivityInjector, HasServiceInjector, HasBroadcastReceiverInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var serviceInjector: DispatchingAndroidInjector<Service>
    @Inject lateinit var receiverInjector: DispatchingAndroidInjector<BroadcastReceiver>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun activityInjector() = activityInjector
    override fun serviceInjector() = serviceInjector
    override fun broadcastReceiverInjector() = receiverInjector

}