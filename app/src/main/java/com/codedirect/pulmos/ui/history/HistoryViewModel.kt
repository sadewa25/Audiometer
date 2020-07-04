package com.codedirect.pulmos.ui.history

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.codedirect.pulmos.R
import com.codedirect.pulmos.data.source.AppRepository
import com.codedirect.pulmos.data.source.remote.response.DataItems
import com.codedirect.pulmos.data.source.remote.response.ResponseJSON
import com.codedirect.pulmos.utils.common.Event
import com.codedirect.pulmos.utils.common.Resource
import com.codedirect.pulmos.utils.models.Menus
import kotlinx.coroutines.Dispatchers

class HistoryViewModel(
    private val application: Application?,
    private val repository: AppRepository?
) : ViewModel() {

    val dataMenu by lazy {
        MutableLiveData<List<Menus>>().apply {
            value = listOf(
                Menus(R.drawable.ic_home, application?.getString(R.string.reporting_symptoms)),
                Menus(R.drawable.ic_home, application?.getString(R.string.reporting_needed))
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
            val temp = repository?.getReportNeededByPatient(
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