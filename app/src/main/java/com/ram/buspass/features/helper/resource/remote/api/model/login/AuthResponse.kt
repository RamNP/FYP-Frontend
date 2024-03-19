package com.ram.buspass.features.helper.resource.remote.api.model.login

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token")
    val accessToken: String? = null,
    @SerializedName("role")
    val role: String? = null
)