package com.ticketswap.assessment.di

import com.ticketswap.assessment.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * inject activities
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [FragmentProvider::class])
    abstract fun bindMainActivity(): MainActivity

}