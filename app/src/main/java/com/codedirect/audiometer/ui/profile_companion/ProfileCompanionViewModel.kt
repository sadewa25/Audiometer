package com.codedirect.audiometer.ui.profile_companion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codedirect.audiometer.data.source.AppRepository
import com.codedirect.audiometer.data.source.remote.response.DataItems
import com.codedirect.audiometer.data.source.remote.response.ResponseJSON
import com.codedirect.audiometer.utils.common.Event

class ProfileCompanionViewModel(private val repository: AppRepository?) : ViewModel() {

    private val _openProfile by lazy { MutableLiveData<Event<Unit>>() }
    val openProfile: MutableLiveData<Event<Unit>>
        get() = _openProfile

    fun _openProfileCompanion() {
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

}