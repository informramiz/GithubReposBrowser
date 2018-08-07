package io.informramiz.githubreposbrowser.di

import android.app.Application
import io.informramiz.githubreposbrowser.GithubReposBrowserApplication
import io.informramiz.githubreposbrowser.di.components.DaggerAppComponent


/**
 * Created by Ramiz Raja on 07/08/2018.
 */
object AppInjector {
    fun init(application: GithubReposBrowserApplication) {
         DaggerAppComponent.builder()
                 .application(application)
                 .build()
                 .inject(application)
    }
}