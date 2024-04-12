package com.ram.buspass.helper.resource.remote.api.model.updateBusLocation

import com.google.gson.annotations.SerializedName

data class LocationPojo(
	@SerializedName("message")
	val message: String? = null,
	@SerializedName("is_success")
	val is_success: Boolean? = null,
	@SerializedName("status")
	val status: Int? = null
)

