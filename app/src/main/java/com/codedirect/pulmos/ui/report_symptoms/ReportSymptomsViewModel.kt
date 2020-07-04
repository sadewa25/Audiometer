package com.codedirect.pulmos.ui.report_symptoms

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.codedirect.pulmos.data.source.AppRepository
import com.codedirect.pulmos.data.source.remote.response.DataItems
import com.codedirect.pulmos.utils.common.Event
import com.codedirect.pulmos.utils.common.Resource
import kotlinx.coroutines.Dispatchers


class ReportSymptomsViewModel(
    private val repository: AppRepository?
) : ViewModel() {

    private val _openReportSymptoms by lazy { MutableLiveData<Event<Unit>>() }
    val openReportSymptoms: MutableLiveData<Event<Unit>>
        get() = _openReportSymptoms

    fun _openReportSymptoms() {
        _openReportSymptoms.value = Event(Unit)
    }

    private var coughOptionsSelected = ObservableInt()
    fun setCoughOptionsSelected(value: Int) {
        coughOptionsSelected.set(value)
    }

    fun getCoughOptionsSelected(): Int {
        return coughOptionsSelected.get()
    }

    private var feverOptionsSelected = ObservableInt()
    fun setfeverOptionsSelected(value: Int) {
        feverOptionsSelected.set(value)
    }

    fun getfeverOptionsSelected(): Int {
        return feverOptionsSelected.get()
    }

    private var limpOptionsSelected = ObservableInt()
    fun setlimpOptionsSelected(value: Int) {
        limpOptionsSelected.set(value)
    }

    fun getlimpOptionsSelected(): Int {
        return limpOptionsSelected.get()
    }

    private var nauseaOptionsSelected = ObservableInt()
    fun setnauseaOptionsSelected(value: Int) {
        nauseaOptionsSelected.set(value)
    }

    fun getnauseaOptionsSelected(): Int {
        return nauseaOptionsSelected.get()
    }

    private var headacheOptionsSelected = ObservableInt()
    fun setheadacheOptionsSelected(value: Int) {
        headacheOptionsSelected.set(value)
    }

    fun getheadacheOptionsSelected(): Int {
        return headacheOptionsSelected.get()
    }

    private var breathlessOptionsSelected = ObservableInt()
    fun setbreathlessOptionsSelected(value: Int) {
        breathlessOptionsSelected.set(value)
    }

    fun getbreathlessOptionsSelected(): Int {
        return breathlessOptionsSelected.get()
    }

    fun createReportSymptoms(datas: DataItems) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository?.createReportSymptoms(datas)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun createCompanionReportSymptoms(datas: DataItems) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository?.companionCreateSymptoms(datas)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}