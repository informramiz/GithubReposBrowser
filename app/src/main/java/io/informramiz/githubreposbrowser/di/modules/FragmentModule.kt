package io.informramiz.githubreposbrowser.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.informramiz.githubreposbrowser.ui.repo.RepoFragment
import io.informramiz.githubreposbrowser.ui.search.SearchFragment


/**
 * Created by Ramiz Raja on 07/08/2018.
 */

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment
    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): RepoFragment
}