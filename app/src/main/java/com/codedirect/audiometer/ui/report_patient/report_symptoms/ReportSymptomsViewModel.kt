package com.codedirect.audiometer.ui.report_patient.report_symptoms

import android.app.Application
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codedirect.audiometer.data.source.AppRepository
import com.codedirect.audiometer.utils.common.Event


class ReportSymptomsViewModel(
    private val repository: AppRepository?,
    private val app: Application
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

}