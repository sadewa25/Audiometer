package com.codedirect.audiometer.di

import com.codedirect.audiometer.data.source.AppRepository
import com.codedirect.audiometer.data.source.remote.RemoteRepository
import com.codedirect.audiometer.data.source.remote.RetrofitClient
import com.codedirect.audiometer.ui.dashboard_patient.DashboardPatientViewModel
import com.codedirect.audiometer.ui.login.LoginViewModel
import com.codedirect.audiometer.ui.report_patient.ReportPatientViewModel
import com.codedirect.audiometer.ui.report_patient.report_needed.ReportNeededViewModel
import com.codedirect.audiometer.ui.report_patient.report_symptoms.ReportSymptomsViewModel
import org.koin.dsl.module

object AppModule {
    val view_models = module {
        single { LoginViewModel(get()) }
        single { DashboardPatientViewModel(get()) }
        single { ReportPatientViewModel(get()) }
        single { ReportSymptomsViewModel(get()) }
        single { ReportNeededViewModel(get()) }
    }


    val repositoryModule = module {
        fun provideRepository(): AppRepository? {
            val remoteRepository =
                RemoteRepository.getInstance(RetrofitClient())
            return remoteRepository.let { localrepo ->
                AppRepository.getInstance(remoteRepository)
            }
        }
        single { provideRepository() }
    }
}