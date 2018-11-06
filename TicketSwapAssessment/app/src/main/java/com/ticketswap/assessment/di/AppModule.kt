package com.ticketswap.assessment.di

import android.arch.persistence.room.Room
import android.content.Context
import com.mydigipay.domainretrofit.SearchUseCaseImpl
import com.squareup.moshi.Moshi
import com.ticketswap.assessment.App
import com.ticketswap.assessment.SpotifyApiConstants
import com.ticketswap.assessment.data.persistance.SpotifyDatabase
import com.ticketswap.assessment.data.persistance.dao.ArtistDao
import com.ticketswap.assessment.data.persistance.dao.UserDao
import com.ticketswap.assessment.datanetwork.SpotifyApi
import com.ticketswap.assessment.domain.usecase.SearchUseCase
import com.ticketswap.assessment.interceptor.AuthorizationInterceptor
import com.ticketswap.assessment.network.client.ClientImpl
import com.ticketswap.assessment.network.client.NetworkClient
import com.ticketswap.assessment.repo.*
import com.ticketswap.assessment.repository_room.*
import com.ticketswap.assessment.view.image.ImageLoader
import com.ticketswap.assessment.view.image.ImageLoaderPicasso
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

/**
 * provides all the app level dependencies
 */
@Module(includes = [ViewModelModule::class])
class AppModule {
    @Provides
    fun provideContext(application: App): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideDatabase(context: Context): SpotifyDatabase = Room
            .databaseBuilder(context, SpotifyDatabase::class.java, "spotify-client")
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    @Named("user")
    internal fun providedao(spotifyDatabase: SpotifyDatabase): UserDao = spotifyDatabase.userDao

    @Singleton
    @Provides
    @Named("artist")
    fun provideArtistDao(spotifyDatabase: SpotifyDatabase): ArtistDao = spotifyDatabase.artistDao

    @Singleton
    @Provides
    @Named("io")
    fun provideIO() = Schedulers.io()

    @Singleton
    @Provides
    @Named("main")
    fun provideMainScheduler() = AndroidSchedulers.mainThread()

    @Provides
    fun provideUserAuthenticatedRepo(@Named("user") userDao: UserDao, @Named("io") io: Scheduler, @Named("main") main: Scheduler): UserAuthenticatedRepository =
            UserAuthenticatedRepositoryImpl(userDao, io, main)

    @Provides
    fun provideSaveTokenRepository(@Named("user") userDao: UserDao, @Named("io") io: Scheduler,
                                   @Named("main") main: Scheduler): SaveTokenRepository =
            SaveTokenRepositoryImpl(userDao, io, main)

    @Provides
    fun provideClient(networkClient: NetworkClient, @Named("io") io: Scheduler,
                      @Named("io") main: Scheduler): SpotifyApi = ClientImpl(networkClient, io, main)

    @Provides
    fun provideNetworkClient(okHttpClient: OkHttpClient, moshi: Moshi): NetworkClient =
            NetworkClient(okHttpClient, moshi, SpotifyApiConstants.BASE_URL)

    @Provides
    @Singleton
    fun provideMoshi() = Moshi.Builder().build()

    @Provides
    fun provideSearchUseCase(spotifyApi: SpotifyApi): SearchUseCase = SearchUseCaseImpl(spotifyApi)

    @Provides
    @Singleton
    fun provideOkHttp(@Named("authorization") authorizationInterceptor: AuthorizationInterceptor,
                      @Named("logger") httpLoggingInterceptor: HttpLoggingInterceptor) =
            OkHttpClient.Builder().addInterceptor(authorizationInterceptor)
                    .addInterceptor(httpLoggingInterceptor).build()

    @Provides
    @Named("authorization")
    fun provideAuthorizationInterceptor(authenticatedRepository: UserAuthenticatedRepository) =
            AuthorizationInterceptor(authenticatedRepository)

    @Provides
    @Named("logger")
    fun provideLoggerIntercepter() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideSearchRepository(@Named("artist") artistDao: ArtistDao): SearchRepository =
            SearchRepositoryImpl(artistDao)

    @Provides
    fun provideArtistRepository(@Named("artist") artistDao: ArtistDao): InsertArtistRepository =
            InsertArtistRepositoryImpl(artistDao)

    @Provides
    fun provideClearUserInfo(@Named("user") userDao: UserDao,
                             @Named("io") io: Scheduler,
                             @Named("main") main: Scheduler): ClearUserInfoRepository =
            ClearUserInfoRepositoryImpl(userDao, io, main)

    @Provides
    fun provideImageLoader(context: Context): ImageLoader = ImageLoaderPicasso(context)
}