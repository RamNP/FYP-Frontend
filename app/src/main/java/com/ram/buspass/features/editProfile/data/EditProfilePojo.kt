package com.ram.buspass.features.editProfile.data

import com.google.gson.annotations.SerializedName

data class EditProfilePojo(
	@SerializedName("message")
	val message: String? = null,
	@SerializedName("is_success")
	val is_success: Boolean? = null,
	@SerializedName("status")
	val status: Int? = null
)

