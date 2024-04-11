package com.ram.buspass.features.editProfile.domain

import com.google.gson.annotations.SerializedName

data class EditModelDto(
    @SerializedName("email")
    val email : String? = null,
    @SerializedName("username")
    val username: String? = null,


)
