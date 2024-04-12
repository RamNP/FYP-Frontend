package com.ram.buspass.features.login.data

import com.google.gson.annotations.SerializedName

data class LoginPojo(
    @SerializedName("is_success")
    val isSuccess: Boolean? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("auth")
    val authResponse: AuthResponse? = null
)