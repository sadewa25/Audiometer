package com.codedirect.audiometer.data.source

import com.codedirect.audiometer.data.source.remote.RemoteRepository
import com.codedirect.audiometer.data.source.remote.response.DataItems
import com.codedirect.audiometer.data.source.remote.response.ResponseJSON
import com.codedirect.audiometer.data.source.remote.response.Users
import com.codedirect.audiometer.utils.ContextProvider

class AppRepository(
    private val remoteRepository: RemoteRepository,
    private val coroutineContext: ContextProvider
) : AppDataSource {

    companion object {
        @Volatile
        private var INSTANCE: AppRepository? = null

        fun getInstance(
            remoteRepository: RemoteRepository
        ): AppRepository = INSTANCE
            ?: synchronized(AppRepository::class.java) {
                AppRepository(
                    remoteRepository,
                    ContextProvider.getInstance()
                ).also {
                    INSTANCE = it
                }
            }
    }

    override suspend fun loginUsers(datas: Users): ResponseJSON {
        return remoteRepository.loginUsers(datas)
    }

    override suspend fun createReportSymptoms(datas: DataItems): ResponseJSON {
        return remoteRepository.createReportSymptoms(datas)
    }

    override suspend fun createReportNeeded(datas: DataItems): ResponseJSON {
        return remoteRepository.createReportNeeded(datas)
    }

    override suspend fun getReportSymptomsByPatient(datas: DataItems): ResponseJSON {
        return remoteRepository.getReportSymptomsByPatient(datas)
    }

    override suspend fun getReportNeededByPatient(datas: DataItems): ResponseJSON {
        return remoteRepository.getReportNeededByPatient(datas)
    }

    override suspend fun getProfile(datas: DataItems): ResponseJSON {
        return remoteRepository.getProfile(datas)
    }

    override suspend fun getChangePassword(datas: DataItems): ResponseJSON {
        return remoteRepository.getChangePassword(datas)
    }

}