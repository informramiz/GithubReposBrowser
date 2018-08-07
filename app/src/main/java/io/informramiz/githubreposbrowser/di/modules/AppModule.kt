package io.informramiz.githubreposbrowser.di.modules

import dagger.Module
import dagger.Provides
import io.informramiz.githubreposbrowser.data.GithubApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by Ramiz Raja on 07/08/2018.
 */

@Module
class AppModule {
    companion object {
        const val GITHUB_API_LINK = "https://api.github.com/"
    }

    @Singleton
    @Provides
    fun provideGithubService(): GithubApiService {
        return Retrofit.Builder()
                .baseUrl(GITHUB_API_LINK)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubApiService::class.java)
    }
}