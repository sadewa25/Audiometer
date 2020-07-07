package com.codedirect.elbicare.data.source.remote

import com.codedirect.elbicare.BuildConfig
import com.codedirect.elbicare.data.source.remote.response.DataItems
import com.codedirect.elbicare.data.source.remote.response.ResponseJSON
import com.codedirect.elbicare.data.source.remote.response.Users
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface API {
    @Headers("API-Key: ${BuildConfig.API_KEY}")
    @POST("user/api/login")
    suspend fun loginUsers(
        @Body data: Users
    ): ResponseJSON

    @Headers("API-Key: ${BuildConfig.API_KEY}")
    @POST("laporanGejalaTBC/api/create")
    suspend fun createReportSymptoms(
        @Body data: DataItems
    ): ResponseJSON

    @Headers("API-Key: ${BuildConfig.API_KEY}")
    @POST("recordsTBC/api/updatePatientRecordToday")
    suspend fun createReportNeeded(
        @Body data: DataItems
    ): ResponseJSON

    @Headers("API-Key: ${BuildConfig.API_KEY}")
    @POST("laporanGejalaTBC/api/findByIdPasien")
    suspend fun getReportSymptomsByPatient(
        @Body data: DataItems
    ): ResponseJSON

    @Headers("API-Key: ${BuildConfig.API_KEY}")
    @POST("recordsTBC/api/findPatientRecordToday")
    suspend fun getReportNeededByPatient(
        @Body data: DataItems
    ): ResponseJSON

    @Headers("API-Key: ${BuildConfig.API_KEY}")
    @POST("pasienTBC/api/getProfile")
    suspend fun getProfile(
        @Body data: DataItems
    ): ResponseJSON

    @Headers("API-Key: ${BuildConfig.API_KEY}")
    @POST("user/api/changePassword")
    suspend fun getChangePassword(
        @Body data: DataItems
    ): ResponseJSON

    @Headers("API-Key: ${BuildConfig.API_KEY}")
    @POST("laporanGejala/api/pendampingCreate")
    suspend fun companionCreateSymptoms(
        @Body data: DataItems
    ): ResponseJSON

    @Headers("API-Key: ${BuildConfig.API_KEY}")
    @POST("laporanKebutuhan/api/pendampingCreate")
    suspend fun companionCreateNeeded(
        @Body data: DataItems
    ): ResponseJSON

    @Headers("API-Key: ${BuildConfig.API_KEY}")
    @POST("pasienCovid/api/findPasienByPendamping")
    suspend fun findPasienByCompanion(
        @Body data: DataItems
    ): ResponseJSON
}