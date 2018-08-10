package io.informramiz.githubreposbrowser.base

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.informramiz.githubreposbrowser.di.Injectable
import javax.inject.Inject


/**
 * Created by Ramiz Raja on 09/08/2018.
 */

abstract class BaseActivity : AppCompatActivity(), Injectable, HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingSupportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingSupportFragmentInjector
}