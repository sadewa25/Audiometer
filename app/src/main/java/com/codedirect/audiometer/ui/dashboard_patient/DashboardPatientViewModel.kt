package com.codedirect.audiometer.ui.dashboard_patient

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codedirect.audiometer.R
import com.codedirect.audiometer.utils.common.Event
import com.codedirect.audiometer.utils.models.Menus

class DashboardPatientViewModel(private val application: Application?) : ViewModel() {

    val dataMenu by lazy {
        MutableLiveData<List<Menus>>().apply {
            value = listOf(
                Menus(R.drawable.ic_report, application?.getString(R.string.menu_report)),
                Menus(R.drawable.ic_history, application?.getString(R.string.menu_history)),
                Menus(R.drawable.ic_profile, application?.getString(R.string.menu_profile))
            )
        }
    }

    private val _openDasboardPatient = MutableLiveData<Event<Menus>>()
    val openDashboardPatient: LiveData<Event<Menus>> = _openDasboardPatient

    fun openDashboardPatient(data: Menus) {
        _openDasboardPatient.value = Event(data)
    }

}