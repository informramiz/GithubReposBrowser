package io.informramiz.githubreposbrowser.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.informramiz.githubreposbrowser.di.ViewModelKey
import io.informramiz.githubreposbrowser.ui.search.SearchViewModel
import io.informramiz.githubreposbrowser.ui.viewmodelfactory.ViewModelFactory


/**
 * Created by Ramiz Raja on 08/08/2018.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}