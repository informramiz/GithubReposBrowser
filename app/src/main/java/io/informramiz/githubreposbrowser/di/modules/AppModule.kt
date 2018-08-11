package io.informramiz.githubreposbrowser.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import io.informramiz.githubreposbrowser.BuildConfig
import io.informramiz.githubreposbrowser.GithubReposBrowserApplication
import io.informramiz.githubreposbrowser.common.AppExecutors
import io.informramiz.githubreposbrowser.data.datasources.local.AppLocalDataSource
import io.informramiz.githubreposbrowser.data.datasources.local.LocalDataSource
import io.informramiz.githubreposbrowser.data.datasources.local.preferences.AppPreferencesHelper
import io.informramiz.githubreposbrowser.data.datasources.local.preferences.PreferencesHelper
import io.informramiz.githubreposbrowser.data.datasources.remote.AppRemoteDataSource
import io.informramiz.githubreposbrowser.data.datasources.remote.RemoteDataSource
import io.informramiz.githubreposbrowser.data.datasources.remote.GithubApiService
import io.informramiz.githubreposbrowser.data.repository.AppDataRepository
import io.informramiz.githubreposbrowser.data.repository.DataRepository
import io.informramiz.githubreposbrowser.data.utils.retrofit.LiveDataCallAdapterFactory
import io.informramiz.githubreposbrowser.di.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

        return Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .client(httpClient)
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

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Singleton
    @Provides
    fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferencesHelper
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(appLocalDataSource: AppLocalDataSource): LocalDataSource {
        return appLocalDataSource
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(appRemoteDataSource: AppRemoteDataSource): RemoteDataSource {
        return appRemoteDataSource
    }

    @Singleton
    @Provides
    fun provideDataRepository(appDataRepository: AppDataRepository): DataRepository {
        return appDataRepository
    }
}