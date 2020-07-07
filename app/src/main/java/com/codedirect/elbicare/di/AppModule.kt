package com.codedirect.elbicare.di

import com.codedirect.elbicare.data.source.AppRepository
import com.codedirect.elbicare.data.source.remote.RemoteRepository
import com.codedirect.elbicare.data.source.remote.RetrofitClient
import com.codedirect.elbicare.ui.dashboard.DashboardViewModel
import com.codedirect.elbicare.ui.history.HistoryViewModel
import com.codedirect.elbicare.ui.login.LoginViewModel
import com.codedirect.elbicare.ui.patient_companion.PatientCompanionViewModel
import com.codedirect.elbicare.ui.profile.ProfileViewModel
import com.codedirect.elbicare.ui.profile_companion.ProfileCompanionViewModel
import com.codedirect.elbicare.ui.report_needed.ReportNeededViewModel
import com.codedirect.elbicare.ui.report_patient.ReportPatientViewModel
import com.codedirect.elbicare.ui.report_symptoms.ReportSymptomsViewModel
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