package com.ram.buspass.features.register.data

import com.google.gson.annotations.SerializedName

data class RegisterPojo(
	@SerializedName("message")
	val message: String? = null,
	@SerializedName("is_success")
	val is_success: Boolean? = null,

)

