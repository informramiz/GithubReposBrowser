package io.informramiz.githubreposbrowser.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import io.informramiz.githubreposbrowser.BuildConfig
import io.informramiz.githubreposbrowser.GithubReposBrowserApplication
import io.informramiz.githubreposbrowser.common.AppExecutors
import io.informramiz.githubreposbrowser.data.remote.GithubApiService
import io.informramiz.githubreposbrowser.di.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by Ramiz Raja on 07/08/2018.
 */

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideGithubService(): GithubApiService {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors
    }

    @Singleton
    @Provides
    @ApplicationContext
    fun provideApplicationContext(githubReposBrowserApplication: GithubReposBrowserApplication): Context {
        return githubReposBrowserApplication
    }
}