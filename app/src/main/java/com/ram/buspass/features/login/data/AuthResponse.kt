package com.ram.buspass.features.login.data

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token")
    val accessToken: String? = null,
    @SerializedName("role")
    val role: String? = null,
    @SerializedName("id")
    val id: Int? = null
)