package com.codedirect.elbicare.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Users(

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("__v")
	val V: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("password")
	val password: String? = null
)