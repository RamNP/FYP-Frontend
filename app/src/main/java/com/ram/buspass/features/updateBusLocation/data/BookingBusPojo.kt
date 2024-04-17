package com.ram.buspass.features.updateBusLocation.data

import com.google.gson.annotations.SerializedName

data class BookingBusPojo(
	@SerializedName("data")
	val data: List<DataItem>? = null,
	@SerializedName("message")
	val message: String? = null,
	@SerializedName("is_success")
	val is_success: Boolean? = null,
	@SerializedName("status")
	val status: Int? = null
)

data class DataItem(
@SerializedName("from_to")
	val from_to: String? = null,
	@SerializedName("route")
	val route: String? = null,
	@SerializedName("bus_speed")
	val bus_speed: String? = null,
	@SerializedName("name")
	val name: String? = null,
	@SerializedName("id")
	val id: Int? = null,
	@SerializedName("bus_number")
	val bus_number: String? = null
)

