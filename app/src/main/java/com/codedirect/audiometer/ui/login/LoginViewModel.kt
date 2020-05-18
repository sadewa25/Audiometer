package com.codedirect.audiometer.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.codedirect.audiometer.data.source.AppRepository
import com.codedirect.audiometer.data.source.remote.response.ResponseJSON
import com.codedirect.audiometer.data.source.remote.response.Users

class LoginViewModel(private val repository: AppRepository?) : ViewModel() {

    fun loginUsers(datas: Users): LiveData<ResponseJSON>? {
        return repository?.loginUsers(datas)
    }

}