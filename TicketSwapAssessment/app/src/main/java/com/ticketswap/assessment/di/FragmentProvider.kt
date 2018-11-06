package com.ticketswap.assessment.di

import com.ticketswap.assessment.view.detail.DetailFragment
import com.ticketswap.assessment.view.login.LoginFragment
import com.ticketswap.assessment.view.search.SearchFragment
import com.ticketswap.assessment.view.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * inject fragments for main activity
 */
@Module
abstract class FragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideSplash(): SplashFragment

    @ContributesAndroidInjector
    abstract fun provideLogin(): LoginFragment

    @ContributesAndroidInjector
    abstract fun provideSearch(): SearchFragment

    @ContributesAndroidInjector
    abstract fun provideDetail(): DetailFragment
}
