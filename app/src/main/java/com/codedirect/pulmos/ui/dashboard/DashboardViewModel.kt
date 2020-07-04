package com.codedirect.pulmos.ui.dashboard

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codedirect.pulmos.R
import com.codedirect.pulmos.utils.common.Event
import com.codedirect.pulmos.utils.models.Menus

class DashboardViewModel(private val application: Application?) : ViewModel() {

    val dataMenu by lazy {
        MutableLiveData<List<Menus>>().apply {
            value = listOf(
                Menus(R.drawable.ic_report, application?.getString(R.string.menu_report)),
                Menus(R.drawable.ic_history, application?.getString(R.string.menu_history)),
                Menus(R.drawable.ic_profile, application?.getString(R.string.menu_profile))
            )
        }
    }

    val dataMenuCompanion by lazy {
        MutableLiveData<List<Menus>>().apply {
            value = listOf(
                Menus(R.drawable.ic_report, application?.getString(R.string.menu_report)),
                Menus(R.drawable.ic_history, application?.getString(R.string.menu_patient)),
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