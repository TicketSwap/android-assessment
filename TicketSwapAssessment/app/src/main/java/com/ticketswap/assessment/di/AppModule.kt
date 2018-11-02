package com.ticketswap.assessment.di

import android.content.Context
import com.ticketswap.assessment.App
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideContext(application: App): Context = application.applicationContext


}