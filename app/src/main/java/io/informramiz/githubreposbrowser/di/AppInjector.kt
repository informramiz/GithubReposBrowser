package io.informramiz.githubreposbrowser.di

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import io.informramiz.githubreposbrowser.GithubReposBrowserApplication
import io.informramiz.githubreposbrowser.di.components.DaggerAppComponent


/**
 * Created by Ramiz Raja on 07/08/2018.
 */
object AppInjector {
    fun init(application: GithubReposBrowserApplication) {
        //inject into application class
        DaggerAppComponent.builder()
                .application(application)
                .build()
                .inject(application)

        //inject into activities
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks{
            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                handleActivityInjection(activity)
                handleFragmentInjection(activity)
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, savedInstanceState: Bundle?) {
            }

        })
    }

    private fun handleActivityInjection(activity: Activity) {
        when(activity) {
            is Injectable, is HasSupportFragmentInjector -> AndroidInjection.inject(activity)
        }
    }

    private fun handleFragmentInjection(activity: Activity) {
        if (activity is AppCompatActivity) {
            activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(
                            object : FragmentManager.FragmentLifecycleCallbacks() {
                                override fun onFragmentAttached(
                                        fm: FragmentManager,
                                        fragment: Fragment,
                                        context: Context) {
                                    if (fragment is Injectable) {
                                        AndroidSupportInjection.inject(fragment)
                                    }
                                    super.onFragmentAttached(fm, fragment, context)
                                }
                            }, true)
        }
    }
}