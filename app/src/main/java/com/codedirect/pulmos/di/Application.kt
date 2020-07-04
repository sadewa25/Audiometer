package com.codedirect.pulmos.di

import android.app.Application
import com.codedirect.pulmos.di.AppModule.repositoryModule
import com.codedirect.pulmos.di.AppModule.view_models
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(view_models, repositoryModule))
        }
    }

}