package com.ram.buspass.features.editProfile.domain

import com.google.gson.annotations.SerializedName

data class EditModelDto(
    @SerializedName("id")
    val id : Int? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("address")
    val address : String? = null,


)
