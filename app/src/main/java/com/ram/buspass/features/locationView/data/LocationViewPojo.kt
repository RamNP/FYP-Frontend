package com.ram.buspass.features.locationView.data

data class LocationViewPojo(
	val bus_details: List<BusDetailsItem>? = null,
	val message: String? = null,
	val is_success: Boolean? = null,
	val status: Int? = null
)

data class BusDetailsItem(
	val from_to: String? = null,
	val route: String? = null,
	val bus_speed: String? = null,
	val latitude: Double? = null,
	val name: String? = null,
	val bus_number: String? = null,
	val longitude: Double? = null
)

