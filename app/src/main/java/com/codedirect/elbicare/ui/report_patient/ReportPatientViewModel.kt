package com.codedirect.elbicare.ui.report_patient

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.codedirect.elbicare.data.source.AppRepository
import com.codedirect.elbicare.data.source.remote.response.DataItems
import com.codedirect.elbicare.utils.common.Event
import com.codedirect.elbicare.utils.common.Resource
import kotlinx.coroutines.Dispatchers

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

    fun createReportNeeded(datas: DataItems) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository?.createReportNeeded(datas)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun loadProfile(datas: DataItems) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository?.getProfile(datas)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}