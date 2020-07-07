package com.codedirect.elbicare.data.source

import com.codedirect.elbicare.data.source.remote.response.DataItems
import com.codedirect.elbicare.data.source.remote.response.ResponseJSON
import com.codedirect.elbicare.data.source.remote.response.Users

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