package com.codedirect.elbicare.di

import android.app.Application
import com.codedirect.elbicare.di.AppModule.repositoryModule
import com.codedirect.elbicare.di.AppModule.view_models
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