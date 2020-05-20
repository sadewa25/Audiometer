package com.codedirect.audiometer.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codedirect.audiometer.data.source.AppRepository
import com.codedirect.audiometer.utils.common.Event

class ProfileViewModel (private val repository: AppRepository?) : ViewModel() {

    private val _openProfile by lazy { MutableLiveData<Event<Unit>>() }
    val openProfile: MutableLiveData<Event<Unit>>
        get() = _openProfile
    fun _openProfile(){
        _openProfile.value = Event(Unit)
    }

}