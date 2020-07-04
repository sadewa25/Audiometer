package com.codedirect.pulmos.data.source.remote

import com.codedirect.pulmos.data.source.remote.response.DataItems
import com.codedirect.pulmos.data.source.remote.response.Users

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

    suspend fun loginUsers(datas: Users) = apiService.response().loginUsers(datas)

    suspend fun createReportSymptoms(datas: DataItems) =
        apiService.response().createReportSymptoms(datas)

    suspend fun createReportNeeded(datas: DataItems) =
        apiService.response().createReportNeeded(datas)

    suspend fun getReportSymptomsByPatient(datas: DataItems) =
        apiService.response().getReportSymptomsByPatient(datas)

    suspend fun getReportNeededByPatient(datas: DataItems) =
        apiService.response().getReportNeededByPatient(datas)

    suspend fun getProfile(datas: DataItems) =
        apiService.response().getProfile(datas)

    suspend fun getChangePassword(datas: DataItems) =
        apiService.response().getChangePassword(datas)

    suspend fun companionCreateSymptoms(datas: DataItems) =
        apiService.response().companionCreateSymptoms(datas)

    suspend fun companionCreateNeeded(datas: DataItems) =
        apiService.response().companionCreateNeeded(datas)

    suspend fun findPasienByCompanion(datas: DataItems) =
        apiService.response().findPasienByCompanion(datas)
}