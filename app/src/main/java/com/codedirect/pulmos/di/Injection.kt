package com.codedirect.pulmos.di

import android.app.Application
import com.codedirect.pulmos.data.source.AppRepository
import com.codedirect.pulmos.data.source.remote.RemoteRepository
import com.codedirect.pulmos.data.source.remote.RetrofitClient

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