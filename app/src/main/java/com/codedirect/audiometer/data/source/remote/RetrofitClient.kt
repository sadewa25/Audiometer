package com.codedirect.audiometer.data.source.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    val url = "http://covid-dashboard-its.id:5000/"

    companion object {
        val ok = OkHttpClient.Builder()
            .connectTimeout(50000, TimeUnit.SECONDS)
            .writeTimeout(50000, TimeUnit.SECONDS)
            .readTimeout(50000, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    fun response(): API {
        val client = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(ok)
            .build()
        return client.create(API::class.java)
    }
}