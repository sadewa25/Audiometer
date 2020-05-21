package com.codedirect.audiometer.data.source

import androidx.lifecycle.LiveData
import com.codedirect.audiometer.data.source.remote.response.DataItems
import com.codedirect.audiometer.data.source.remote.response.ResponseJSON
import com.codedirect.audiometer.data.source.remote.response.Users

interface AppDataSource {
    suspend fun loginUsers(datas: Users): ResponseJSON
    suspend fun createReportSymptoms(datas: DataItems): ResponseJSON
    suspend fun createReportNeeded(datas: DataItems): ResponseJSON
    suspend fun getReportSymptomsByPatient(datas: DataItems): ResponseJSON
    suspend fun getReportNeededByPatient(datas: DataItems): ResponseJSON
    suspend fun getProfile(datas: DataItems): ResponseJSON
    suspend fun getChangePassword(datas: DataItems): ResponseJSON
    suspend fun companionCreateSymptoms(datas: DataItems): ResponseJSON
    suspend fun companionCreateNeeded(datas: DataItems): ResponseJSON
    suspend fun findPasienByCompanion(datas: DataItems): ResponseJSON
}