package com.codedirect.audiometer.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codedirect.audiometer.data.source.remote.response.ResponseJSON
import com.codedirect.audiometer.data.source.remote.response.Users
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class RemoteRepository(private val apiService: RetrofitClient) {
    companion object {
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(apiService: RetrofitClient): RemoteRepository {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(apiService)
            }
            return INSTANCE as RemoteRepository
        }
    }

    fun loginUsers(datas: Users): LiveData<ResponseJSON> {
        val data = MutableLiveData<ResponseJSON>()
        apiService.response()
            .loginUsers(datas).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                data.postValue(it)
            }, {})
        return data
    }
}