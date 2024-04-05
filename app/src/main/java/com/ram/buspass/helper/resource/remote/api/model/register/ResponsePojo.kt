package com.ram.buspass.helper.resource.remote.api.model.register

import com.google.gson.annotations.SerializedName

data class ResponsePojo(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("is_success")
    val  isSuccess: Boolean? = null
)