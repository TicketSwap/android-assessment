package com.ticketswap.assessment.di


import com.ticketswap.assessment.App
import com.ticketswap.assessment.MainActivity
import com.ticketswap.assessment.view.splash.SplashFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class,
    AndroidSupportInjectionModule::class, ActivityBuilder::class])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(application: App): Builder
    }
}

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [SplashProvider::class])
    abstract fun bindMainActivity(): MainActivity

}

@Module
abstract class SplashProvider {
    @ContributesAndroidInjector
    abstract fun provideSplash(): SplashFragment
}



