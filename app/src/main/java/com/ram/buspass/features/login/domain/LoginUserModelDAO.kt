package com.ram.buspass.features.login.domain

import com.google.gson.annotations.SerializedName

data class LoginUserModelDAO(
    @SerializedName("email")
    val email : String? = null,
    @SerializedName("password")
    val password: String? = null,

)
