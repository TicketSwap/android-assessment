package com.ticketswap.assessment.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.ticketswap.assessment.App
import com.ticketswap.assessment.BuildConfig
import com.ticketswap.assessment.R
import com.ticketswap.assessment.spotify.SpotifyApi
import com.ticketswap.assessment.spotify.auth.AuthInterceptor
import com.ticketswap.assessment.spotify.auth.TokenAuthenticator
import com.ticketswap.assessment.token.RealmTokenCache
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: App): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideSpotifyApi(client: OkHttpClient) : SpotifyApi {
        return SpotifyApi(client)
    }

    @Provides
    @Singleton
    fun provideTokenAuthenticator() = TokenAuthenticator()

    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenCache : RealmTokenCache) : AuthInterceptor {
        val interceptor = AuthInterceptor()
        interceptor.tokenCache = tokenCache
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(context : Context, authenticator : TokenAuthenticator, authInterceptor : AuthInterceptor) : OkHttpClient {
        val cacheSize = context.resources.getInteger(R.integer.cache_size).toLong()
        val cacheDir = File(context.cacheDir, "http")
        val cache = Cache(cacheDir, cacheSize)
        return OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .authenticator(authenticator)
                .addInterceptor(authInterceptor)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .build()
    }

    @Provides
    fun providesSharedPreferences(application: App): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    companion object {
        private val TIMEOUT = 15
    }

}