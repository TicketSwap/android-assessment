package com.ticketswap.assessment.di

import com.ticketswap.assessment.view.login.LoginFragment
import com.ticketswap.assessment.view.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    @FragmentScope
    abstract fun bindSplash(): SplashFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun bindLogin(): LoginFragment
}