package com.codedirect.audiometer.ui.dashboard_patient

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codedirect.audiometer.R
import com.codedirect.audiometer.utils.Utils
import com.codedirect.audiometer.utils.dummies.DataMenu
import com.codedirect.audiometer.utils.models.Menus

class DashboardPatientViewModel : ViewModel() {

    val dataMenu by lazy {
        MutableLiveData<List<Menus>>().apply {
            value = listOf(
                Menus(R.drawable.ic_home, "Dashboard"),
                Menus(R.drawable.ic_home, "Dashboard2"),
                Menus(R.drawable.ic_home, "Dashboard3")
            )
        }
    }

    val sample = DataMenu.getData()

}