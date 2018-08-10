package io.informramiz.githubreposbrowser.base

import android.support.v4.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.informramiz.githubreposbrowser.di.Injectable
import javax.inject.Inject


/**
 * Created by Ramiz Raja on 09/08/2018.
 */
abstract class BaseFragment : Fragment(), Injectable, HasSupportFragmentInjector {
    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = childFragmentInjector
}