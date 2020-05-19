package com.codedirect.audiometer.ui.report_patient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codedirect.audiometer.data.source.AppRepository
import com.codedirect.audiometer.utils.common.Event

class ReportPatientViewModel(private val repository: AppRepository?) : ViewModel() {

    private val _openReportPatientSymptoms by lazy { MutableLiveData<Event<Unit>>() }
    val openReportPatientSymptoms: MutableLiveData<Event<Unit>>
        get() = _openReportPatientSymptoms
    fun _openReportPatientSymptoms(){
        _openReportPatientSymptoms.value = Event(Unit)
    }

    private val _openReportPatientNeeded by lazy { MutableLiveData<Event<Unit>>() }
    val openReportPatientNeeded: MutableLiveData<Event<Unit>>
        get() = _openReportPatientNeeded
    fun _openReportPatientNeeded(){
        _openReportPatientNeeded.value = Event(Unit)
    }

}