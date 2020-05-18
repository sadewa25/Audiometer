package com.codedirect.audiometer.ui.dashboard_patient

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codedirect.audiometer.R
import com.codedirect.audiometer.data.source.AppRepository
import com.codedirect.audiometer.utils.models.Menus

class DashboardPatientViewModel(private val application: Application?) : ViewModel() {

    val dataMenu by lazy {
        MutableLiveData<List<Menus>>().apply {
            value = listOf(
                Menus(R.drawable.ic_home, application?.getString(R.string.menu_report)),
                Menus(R.drawable.ic_home, application?.getString(R.string.menu_history)),
                Menus(R.drawable.ic_home, application?.getString(R.string.menu_profile))
            )
        }
    }

    fun onItemClick(data: Menus) {
        Log.i("Informasi ::", data.toString())
    }

}