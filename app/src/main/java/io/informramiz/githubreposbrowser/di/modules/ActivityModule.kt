package io.informramiz.githubreposbrowser.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.informramiz.githubreposbrowser.MainActivity


/**
 * Created by Ramiz Raja on 07/08/2018.
 */

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}