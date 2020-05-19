package com.codedirect.audiometer.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseJSON(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("user")
    val user: Users? = null,

    @field:SerializedName("status")
    val status: Int? = null,

    @field:SerializedName("data")
    val data: List<DataItems?>? = null
)