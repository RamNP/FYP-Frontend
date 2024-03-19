package com.ram.buspass.features.register.domain

import com.google.gson.annotations.SerializedName

data class UserModelDAO(
    @SerializedName("email")
    val email : String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("role")
    val role: String? = null
)
