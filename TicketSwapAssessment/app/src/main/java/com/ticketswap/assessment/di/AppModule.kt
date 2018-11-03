package com.ticketswap.assessment.di

import android.arch.persistence.room.Room
import android.content.Context
import com.ticketswap.assessment.App
import com.ticketswap.assessment.data.persistance.SpotifyDatabase
import com.ticketswap.assessment.data.persistance.dao.UserDao
import com.ticketswap.assessment.repository.UserAuthenticatedRepository
import com.ticketswap.assessment.repository_room.UserAuthenticatedRepositoryImpl
import com.ticketswap.assessment.view.splash.SplashFragment
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideContext(application: App): Context = application.applicationContext

}

@Module
class SplashModule {

    @Provides
    fun provideContext(splashFragment: SplashFragment) = splashFragment.context

    @Provides
    fun provideSpotifyDatabase(context: Context): SpotifyDatabase =
            Room.databaseBuilder(context, SpotifyDatabase::class.java, "spotify-client").build()

    @Provides
    fun provideUserDao(database: SpotifyDatabase): UserDao = database.userDao

    @Provides
    fun provideUserAuthenticatedRepo(userDao: UserDao): UserAuthenticatedRepository =
            UserAuthenticatedRepositoryImpl(userDao)

//    @Provides
//    fun provideSplashViewModel(userAuthenticatedRepository: UserAuthenticatedRepository) = SplashViewModel(userAuthenticatedRepository)
}