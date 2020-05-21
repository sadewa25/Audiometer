package com.codedirect.audiometer.ui.report_needed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.codedirect.audiometer.data.source.AppRepository
import com.codedirect.audiometer.data.source.remote.response.DataItems
import com.codedirect.audiometer.utils.common.Event
import com.codedirect.audiometer.utils.common.Resource
import kotlinx.coroutines.Dispatchers

class ReportNeededViewModel(
    private val repository: AppRepository?
) : ViewModel() {

    private val _openReportNeeded by lazy { MutableLiveData<Event<Unit>>() }
    val openReportSymptoms: MutableLiveData<Event<Unit>>
        get() = _openReportNeeded

    fun _openReportNeeded() {
        _openReportNeeded.value = Event(Unit)
    }

    fun createReportNeeded(datas: DataItems) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository?.createReportNeeded(datas)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}