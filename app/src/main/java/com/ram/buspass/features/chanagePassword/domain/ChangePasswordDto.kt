package com.ram.buspass.features.chanagePassword.domain

import com.google.gson.annotations.SerializedName

 data class  ChangePasswordDto (
    @SerializedName("email")
    val email: String?,
    @SerializedName("old_password")
    val oldPassword: String?,
    @SerializedName("new_password")
    val newPassword: String?
 )