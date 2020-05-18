package com.codedirect.audiometer.data.source.remote

import com.codedirect.audiometer.data.source.remote.response.ResponseJSON
import com.codedirect.audiometer.data.source.remote.response.Users
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface API {
    @Headers("API-Key: \$2a\$10\$0.WlAig6EVWThAKylQ5JEuoTp/QWvGRjiRSfzoxUu8VfkhEpNW2GK")
    @POST("user/api/login")
    fun loginUsers(
        @Body data: Users
    ): Observable<ResponseJSON>
}