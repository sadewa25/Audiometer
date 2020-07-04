package com.codedirect.pulmos.di

import com.codedirect.pulmos.data.source.AppRepository
import com.codedirect.pulmos.data.source.remote.RemoteRepository
import com.codedirect.pulmos.data.source.remote.RetrofitClient
import com.codedirect.pulmos.ui.dashboard.DashboardViewModel
import com.codedirect.pulmos.ui.history.HistoryViewModel
import com.codedirect.pulmos.ui.login.LoginViewModel
import com.codedirect.pulmos.ui.patient_companion.PatientCompanionViewModel
import com.codedirect.pulmos.ui.profile.ProfileViewModel
import com.codedirect.pulmos.ui.profile_companion.ProfileCompanionViewModel
import com.codedirect.pulmos.ui.report_needed.ReportNeededViewModel
import com.codedirect.pulmos.ui.report_patient.ReportPatientViewModel
import com.codedirect.pulmos.ui.report_symptoms.ReportSymptomsViewModel
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