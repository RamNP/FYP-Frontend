package com.ram.buspass.features.updateBusLocation.data

import com.google.gson.annotations.SerializedName

data class LocationPojo(
	@SerializedName("message")
	val message: String? = null,
	@SerializedName("is_success")
	val is_success: Boolean? = null,
	@SerializedName("status")
	val status: Int? = null
)

