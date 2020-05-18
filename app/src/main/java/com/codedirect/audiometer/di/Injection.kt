package com.codedirect.audiometer.di

import android.app.Application
import com.codedirect.audiometer.data.source.AppRepository
import com.codedirect.audiometer.data.source.remote.RemoteRepository
import com.codedirect.audiometer.data.source.remote.RetrofitClient

class Injection {
    companion object {
        fun provideRepository(application: Application?): AppRepository? {
            val remoteRepository = RemoteRepository.getInstance(RetrofitClient())
            return remoteRepository.let {
                AppRepository.getInstance(remoteRepository)
            }
        }
    }
}