package com.codedirect.audiometer.data.source.remote

import com.codedirect.audiometer.BuildConfig
import com.codedirect.audiometer.data.source.remote.response.DataItems
import com.codedirect.audiometer.data.source.remote.response.ResponseJSON
import com.codedirect.audiometer.data.source.remote.response.Users
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
    @POST("laporanGejala/api/create")
    suspend fun createReportSymptoms(
        @Body data: DataItems
    ): ResponseJSON

    @Headers("API-Key: ${BuildConfig.API_KEY}")
    @POST("laporanKebutuhan/api/create")
    suspend fun createReportNeeded(
        @Body data: DataItems
    ): ResponseJSON

    @Headers("API-Key: ${BuildConfig.API_KEY}")
    @POST("laporanGejala/api/findByIdPasien")
    suspend fun getReportSymptomsByPatient(
        @Body data: DataItems
    ): ResponseJSON

    @Headers("API-Key: ${BuildConfig.API_KEY}")
    @POST("laporanKebutuhan/api/findByIdPasien")
    suspend fun getReportNeededByPatient(
        @Body data: DataItems
    ): ResponseJSON

    @Headers("API-Key: ${BuildConfig.API_KEY}")
    @POST("pasienCovid/api/getProfile")
    suspend fun getProfile(
        @Body data: DataItems
    ): ResponseJSON

    @Headers("API-Key: ${BuildConfig.API_KEY}")
    @POST("user/api/changePassword")
    suspend fun getChangePassword(
        @Body data: DataItems
    ): ResponseJSON
}