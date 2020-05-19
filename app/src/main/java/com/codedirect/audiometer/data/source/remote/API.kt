package com.codedirect.audiometer.data.source.remote

import com.codedirect.audiometer.data.source.remote.response.DataItems
import com.codedirect.audiometer.data.source.remote.response.ResponseJSON
import com.codedirect.audiometer.data.source.remote.response.Users
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface API {
    @Headers("API-Key: \$2a\$10\$0.WlAig6EVWThAKylQ5JEuoTp/QWvGRjiRSfzoxUu8VfkhEpNW2GK")
    @POST("user/api/login")
    suspend fun loginUsers(
        @Body data: Users
    ): ResponseJSON

    @Headers("API-Key: \$2a\$10\$0.WlAig6EVWThAKylQ5JEuoTp/QWvGRjiRSfzoxUu8VfkhEpNW2GK")
    @POST("laporanGejala/api/create")
    suspend fun createReportSymptoms(
        @Body data: DataItems
    ): ResponseJSON

    @Headers("API-Key: \$2a\$10\$0.WlAig6EVWThAKylQ5JEuoTp/QWvGRjiRSfzoxUu8VfkhEpNW2GK")
    @POST("laporanKebutuhan/api/create")
    suspend fun createReportNeeded(
        @Body data: DataItems
    ): ResponseJSON
}