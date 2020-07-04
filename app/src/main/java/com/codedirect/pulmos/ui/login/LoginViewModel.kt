package com.codedirect.pulmos.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.codedirect.pulmos.data.source.AppRepository
import com.codedirect.pulmos.data.source.remote.response.Users
import com.codedirect.pulmos.utils.common.Resource
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val repository: AppRepository?) : ViewModel() {

    fun getUsers(datas: Users) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository?.loginUsers(datas)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}