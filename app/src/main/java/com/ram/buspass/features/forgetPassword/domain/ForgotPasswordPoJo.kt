package com.ram.buspass.features.forgetPassword.domain

import com.google.gson.annotations.SerializedName

data class ForgotPasswordPoJo(
	@SerializedName("message")
	val message: String? = null,
	@SerializedName("is_success")
	val is_success: Boolean? = null,
	@SerializedName("status")
	val status: Int? = null
)

