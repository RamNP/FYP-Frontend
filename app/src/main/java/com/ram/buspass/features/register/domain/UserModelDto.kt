package com.ram.buspass.features.register.domain

import com.google.gson.annotations.SerializedName

data class UserModelDto(
    @SerializedName("email")
    val email : String? = null,
    @SerializedName("username")
    val username : String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("role")
    val role: String? = null
)
