package com.codedirect.audiometer.ui.patient_companion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.codedirect.audiometer.data.source.AppRepository
import com.codedirect.audiometer.data.source.remote.response.DataItems
import com.codedirect.audiometer.data.source.remote.response.ResponseJSON
import com.codedirect.audiometer.utils.common.Resource
import kotlinx.coroutines.Dispatchers

class PatientCompanionViewModel(private val repository: AppRepository?) : ViewModel() {

    private val _dataPatient =
        MutableLiveData<List<DataItems>>().apply { value = emptyList() }
    val dataPatient: LiveData<List<DataItems>> = _dataPatient

    fun setDataPatient(data: ResponseJSON?) {
        _dataPatient.apply {
            value = data?.data as List<DataItems>?
        }
    }

    fun getDataPatintByCompanion(dataItems: DataItems) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val temp = repository?.findPasienByCompanion(
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