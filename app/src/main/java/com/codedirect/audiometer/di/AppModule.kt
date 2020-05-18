package com.codedirect.audiometer.di

import android.app.Application
import com.codedirect.audiometer.data.source.AppRepository
import com.codedirect.audiometer.data.source.remote.RemoteRepository
import com.codedirect.audiometer.data.source.remote.RetrofitClient
import com.codedirect.audiometer.ui.dashboard_patient.DashboardPatientViewModel
import com.codedirect.audiometer.ui.login.LoginViewModel
import org.koin.dsl.module

object AppModule {
    val view_models = module {
        single { LoginViewModel(get()) }
        single { DashboardPatientViewModel() }
    }


    val repositoryModule = module {
        fun provideRepository(application: Application?): AppRepository? {
            val remoteRepository =
                RemoteRepository.getInstance(RetrofitClient())
            return remoteRepository.let { localrepo ->
                AppRepository.getInstance(remoteRepository)
            }
        }
        single { provideRepository(get()) }
    }
}