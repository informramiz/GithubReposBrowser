package io.informramiz.githubreposbrowser.di.components

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.informramiz.githubreposbrowser.GithubReposBrowserApplication
import io.informramiz.githubreposbrowser.di.modules.ActivityModule
import io.informramiz.githubreposbrowser.di.modules.AppModule


/**
 * Created by Ramiz Raja on 07/08/2018.
 */
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: GithubReposBrowserApplication): Builder
        fun build(): AppComponent
    }

    fun inject(app: GithubReposBrowserApplication)
}