package com.codedirect.audiometer.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codedirect.audiometer.data.source.AppRepository
import com.codedirect.audiometer.di.Injection
import com.codedirect.audiometer.ui.dashboard_patient.DashboardPatientViewModel
import com.codedirect.audiometer.ui.history.HistoryViewModel
import com.codedirect.audiometer.ui.login.LoginViewModel
import com.codedirect.audiometer.ui.profile.ProfileViewModel
import com.codedirect.audiometer.ui.report_patient.ReportPatientViewModel
import com.codedirect.audiometer.ui.report_patient.report_needed.ReportNeededViewModel
import com.codedirect.audiometer.ui.report_patient.report_symptoms.ReportSymptomsViewModel

class ViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    @Volatile
    private var INSTANCE: ViewModelFactory? = null

    private var appRepository: AppRepository? = null
    private var application: Application? = null

    constructor(appRepository: AppRepository?, application: Application?) : this() {
        this.appRepository = appRepository
        this.application = application
    }

    fun getInstance(application: Application?): ViewModelFactory? {
        if (INSTANCE == null) {
            synchronized(ViewModelFactory::class.java) {
                INSTANCE = ViewModelFactory(Injection.provideRepository(application), application)
            }
        }
        return INSTANCE
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(DashboardPatientViewModel::class.java)) {
            return DashboardPatientViewModel(application) as T
        } else if (modelClass.isAssignableFrom(ReportPatientViewModel::class.java)) {
            return ReportPatientViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(ReportSymptomsViewModel::class.java)) {
            return ReportSymptomsViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(ReportNeededViewModel::class.java)) {
            return ReportNeededViewModel(appRepository) as T
        } else if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(application, appRepository) as T
        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(appRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}