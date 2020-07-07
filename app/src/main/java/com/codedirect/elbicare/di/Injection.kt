package com.codedirect.elbicare.di

import android.app.Application
import com.codedirect.elbicare.data.source.AppRepository
import com.codedirect.elbicare.data.source.remote.RemoteRepository
import com.codedirect.elbicare.data.source.remote.RetrofitClient

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