package com.codedirect.elbicare.ui.history

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.codedirect.elbicare.R
import com.codedirect.elbicare.data.source.AppRepository
import com.codedirect.elbicare.data.source.remote.response.DataItems
import com.codedirect.elbicare.data.source.remote.response.ResponseJSON
import com.codedirect.elbicare.utils.common.Event
import com.codedirect.elbicare.utils.common.Resource
import com.codedirect.elbicare.utils.models.Menus
import kotlinx.coroutines.Dispatchers

class HistoryViewModel(
    private val application: Application?,
    private val repository: AppRepository?
) : ViewModel() {

    val dataMenu by lazy {
        MutableLiveData<List<Menus>>().apply {
            value = listOf(
                Menus(R.drawable.ic_report_symptoms, application?.getString(R.string.reporting_symptoms)),
                Menus(R.drawable.ic_repot_needed, application?.getString(R.string.reporting_needed))
            )
        }
    }

    private val _dataReportSymptoms =
        MutableLiveData<List<DataItems>>().apply { value = emptyList() }
    val dataReportSymptoms: LiveData<List<DataItems>> = _dataReportSymptoms

    fun setDataReportSymptoms(data: ResponseJSON?) {
        _dataReportSymptoms.apply {
            value = data?.data as List<DataItems>?
        }
    }

    private val _dataReportNeeded =
        MutableLiveData<List<DataItems>>().apply { value = emptyList() }
    val dataReportNeeded: LiveData<List<DataItems>> = _dataReportNeeded

    fun setDataReportNeeded(data: ResponseJSON?) {
        _dataReportNeeded.apply {
            value = data?.data as List<DataItems>?
        }
    }

    private val _openHistoryReportPatient = MutableLiveData<Event<Menus>>()
    val openHistoryReportPatient: LiveData<Event<Menus>> = _openHistoryReportPatient

    fun openHistoryReportPatient(data: Menus) {
        _openHistoryReportPatient.value = Event(data)
    }

    fun getReportSymptomsByPatient(dataItems: DataItems) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val temp = repository?.getReportSymptomsByPatient(
                dataItems
            )
            emit(
                Resource.success(
                    data = temp
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getReportNeededByPatient(dataItems: DataItems) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val temp = repository?.getProfile(
                dataItems
            )
            emit(
                Resource.success(
                    data = temp
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}