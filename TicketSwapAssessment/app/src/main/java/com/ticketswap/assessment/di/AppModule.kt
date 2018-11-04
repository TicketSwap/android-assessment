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
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
    internal fun providedao(context: Context): UserDao = Room
            .databaseBuilder(context, SpotifyDatabase::class.java, "spotify-client").build().userDao

    @Singleton
    @Provides
    @Named("io")
    fun provideIO() = Schedulers.io()

    @Singleton
    @Provides
    @Named("main")
    fun provideMainScheduler() = AndroidSchedulers.mainThread()

    @Provides
    fun provideUserAuthenticatedRepo(userDao: UserDao, @Named("io") io: Scheduler, @Named("main") main: Scheduler): UserAuthenticatedRepository =
            UserAuthenticatedRepositoryImpl(userDao, io, main)

}