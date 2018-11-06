package com.ticketswap.assessment.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ticketswap.assessment.view.detail.DetailViewModel
import com.ticketswap.assessment.view.login.LoginHelperViewModel
import com.ticketswap.assessment.view.login.LoginViewModel
import com.ticketswap.assessment.view.search.SearchViewModel
import com.ticketswap.assessment.view.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * provide view models for view model factory class
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun login(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginHelperViewModel::class)
    abstract fun loginhelper(viewModel: LoginHelperViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun search(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun detail(viewModel: DetailViewModel): ViewModel
}