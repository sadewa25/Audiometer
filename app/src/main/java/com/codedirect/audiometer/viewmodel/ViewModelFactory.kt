package com.codedirect.audiometer.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codedirect.audiometer.data.source.AppRepository
import com.codedirect.audiometer.di.Injection
import com.codedirect.audiometer.ui.dashboard_patient.DashboardPatientViewModel
import com.codedirect.audiometer.ui.login.LoginViewModel

class ViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    @Volatile
    private var INSTANCE: ViewModelFactory? = null

    private var mAcademyRepository: AppRepository? = null

    constructor(mAcademyRepository: AppRepository?) : this() {
        this.mAcademyRepository = mAcademyRepository
    }

    fun getInstance(application: Application?): ViewModelFactory? {
        if (INSTANCE == null) {
            synchronized(ViewModelFactory::class.java) {
                INSTANCE = ViewModelFactory(Injection.provideRepository(application))
            }
        }
        return INSTANCE
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(mAcademyRepository) as T
        } else if (modelClass.isAssignableFrom(DashboardPatientViewModel::class.java)) {
            return DashboardPatientViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}