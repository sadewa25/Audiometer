package com.codedirect.audiometer.ui.report_patient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codedirect.audiometer.data.source.AppRepository
import com.codedirect.audiometer.utils.common.Event

class ReportPatientViewModel(private val repository: AppRepository?) : ViewModel() {

    val openReportPatientSymptoms: LiveData<Event<Unit>> = MutableLiveData()
    val openReportPatientNeeded: LiveData<Event<Unit>> = MutableLiveData()

}