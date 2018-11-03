package com.ticketswap.assessment.di

import android.arch.persistence.room.Room
import android.content.Context
import com.ticketswap.assessment.App
import com.ticketswap.assessment.data.persistance.SpotifyDatabase
import com.ticketswap.assessment.data.persistance.dao.UserDao
import com.ticketswap.assessment.repo.UserAuthenticatedRepository
import com.ticketswap.assessment.repository_room.UserAuthenticatedRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Provides
    fun provideContext(application: App): Context = application.applicationContext

    @Singleton
    @Provides
    internal fun providedao(context: Context): UserDao = Room
            .databaseBuilder(context, SpotifyDatabase::class.java, "spotify-client").build().userDao

    //    @Singleton
//    @Provides
//    fun provideUserDao(spotifyDatabase: SpotifyDatabase): UserDao = spotifyDatabase.userDao
//    //
//
    @Provides
    fun provideUserAuthenticatedRepo(userDao: UserDao): UserAuthenticatedRepository = UserAuthenticatedRepositoryImpl(userDao)

//    @Provides
//    fun provideSplashViewModel(userAuthenticatedRepository: UserAuthenticatedRepository) = SplashViewModel(userAuthenticatedRepository)
}