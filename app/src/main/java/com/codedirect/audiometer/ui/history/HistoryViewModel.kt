package com.codedirect.audiometer.ui.history

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.codedirect.audiometer.R
import com.codedirect.audiometer.data.source.AppRepository
import com.codedirect.audiometer.data.source.remote.response.DataItems
import com.codedirect.audiometer.data.source.remote.response.ResponseJSON
import com.codedirect.audiometer.utils.SessionManager
import com.codedirect.audiometer.utils.common.Event
import com.codedirect.audiometer.utils.common.Resource
import com.codedirect.audiometer.utils.models.Menus
import kotlinx.coroutines.Dispatchers

class HistoryViewModel(
    private val application: Application?,
    private val repository: AppRepository?
) : ViewModel() {

    val dataMenu by lazy {
        MutableLiveData<List<Menus>>().apply {
            value = listOf(
                Menus(R.drawable.ic_home, application?.getString(R.string.report_symptoms)),
                Menus(R.drawable.ic_home, application?.getString(R.string.report_needed))
            )
        }
    }

    private val sessionManager by lazy {
        SessionManager(application?.applicationContext)
    }
    private val _dataReportSymptoms =
        MutableLiveData<List<DataItems>>().apply { value = emptyList() }
    val dataReportSymptoms: LiveData<List<DataItems>> = _dataReportSymptoms
//    val dataReportSymptoms by lazy {
//        MutableLiveData<List<DataItems>>().apply {
//            value = listOf(
//                DataItems(batuk = "ya",mual = "ya",pusing = "ya",id = "00",demam = "ya",lemas = "tidak",sesak = "tidak"),
//                DataItems(batuk = "ya",mual = "ya",pusing = "ya",id = "10",demam = "ya",lemas = "tidak",sesak = "tidak"),
//                DataItems(batuk = "ya",mual = "ya",pusing = "ya",id = "20",demam = "ya",lemas = "tidak",sesak = "tidak")
//            )
//        }
//    }

    fun setDataReportSymptoms(data: ResponseJSON?) {
        this._dataReportSymptoms.value = data?.data as List<DataItems>?
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

}