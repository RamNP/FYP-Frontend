package com.ram.buspass.helper.resource.remote.api.model.login

import com.google.gson.annotations.SerializedName

data class LoginPojo(
    @SerializedName("is_success")
    val isSuccess: Boolean? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("auth")
    val authResponse: AuthResponse? = null
)