package io.informramiz.githubreposbrowser

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.informramiz.githubreposbrowser.di.AppInjector
import javax.inject.Inject


/**
 * Created by Ramiz Raja on 07/08/2018.
 */

class GithubReposBrowserApplication : Application(), HasActivityInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

}