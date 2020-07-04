package com.codedirect.pulmos.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.codedirect.pulmos.data.source.AppRepository
import com.codedirect.pulmos.data.source.remote.response.DataItems
import com.codedirect.pulmos.data.source.remote.response.ResponseJSON
import com.codedirect.pulmos.utils.common.Event
import com.codedirect.pulmos.utils.common.Resource
import kotlinx.coroutines.Dispatchers

class ProfileViewModel(private val repository: AppRepository?) : ViewModel() {

    private val _openProfile by lazy { MutableLiveData<Event<Unit>>() }
    val openProfile: MutableLiveData<Event<Unit>>
        get() = _openProfile

    fun _openProfile() {
        _openProfile.value = Event(Unit)
    }

    private val _openChangePassword by lazy { MutableLiveData<Event<Unit>>() }
    val openChangePassword: MutableLiveData<Event<Unit>>
        get() = _openChangePassword

    fun _openChangePassword() {
        _openChangePassword.value = Event(Unit)
    }

    private val _openSubmitChangePassword by lazy { MutableLiveData<Event<Unit>>() }
    val openSubmitChangePassword: MutableLiveData<Event<Unit>>
        get() = _openSubmitChangePassword

    fun _openSubmitChangePassword() {
        _openSubmitChangePassword.value = Event(Unit)
    }

    private val _dataProfile =
        MutableLiveData<List<DataItems>>().apply { value = emptyList() }
    val dataProfile: LiveData<List<DataItems>> = _dataProfile

    fun setDataProfile(data: ResponseJSON?) {
        _dataProfile.apply {
            value = data?.data as List<DataItems>?
        }
    }

    fun getProfile(datas: DataItems) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository?.getProfile(datas)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getChangePassword(datas: DataItems) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository?.getChangePassword(datas)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


}