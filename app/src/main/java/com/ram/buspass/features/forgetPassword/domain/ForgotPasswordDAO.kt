package com.ram.buspass.features.forgetPassword.domain

import com.google.gson.annotations.SerializedName

data class ForgotPasswordDAO (
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("new_password")
    val newPassword: String? = null
)
