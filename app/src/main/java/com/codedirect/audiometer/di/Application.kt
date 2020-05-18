package com.codedirect.audiometer.di

import android.app.Application
import com.codedirect.audiometer.di.AppModule.repositoryModule
import com.codedirect.audiometer.di.AppModule.view_models
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