package com.codedirect.audiometer.di

import com.codedirect.audiometer.data.source.AppRepository
import com.codedirect.audiometer.data.source.remote.RemoteRepository
import com.codedirect.audiometer.data.source.remote.RetrofitClient
import com.codedirect.audiometer.ui.dashboard.DashboardViewModel
import com.codedirect.audiometer.ui.history.HistoryViewModel
import com.codedirect.audiometer.ui.login.LoginViewModel
import com.codedirect.audiometer.ui.patient_companion.PatientCompanionViewModel
import com.codedirect.audiometer.ui.profile.ProfileViewModel
import com.codedirect.audiometer.ui.profile_companion.ProfileCompanionViewModel
import com.codedirect.audiometer.ui.report_needed.ReportNeededViewModel
import com.codedirect.audiometer.ui.report_patient.ReportPatientViewModel
import com.codedirect.audiometer.ui.report_symptoms.ReportSymptomsViewModel
import org.koin.dsl.module

object AppModule {
    val view_models = module {
        single { LoginViewModel(get()) }
        single {
            DashboardViewModel(
                get()
            )
        }
        single { ReportPatientViewModel(get()) }
        single { ReportSymptomsViewModel(get()) }
        single { ReportNeededViewModel(get()) }
        single { HistoryViewModel(get(), get()) }
        single { ProfileViewModel(get()) }
        single { PatientCompanionViewModel(get()) }
        single { ProfileCompanionViewModel(get()) }
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