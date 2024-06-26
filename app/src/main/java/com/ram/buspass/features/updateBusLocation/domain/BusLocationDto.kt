package com.ram.buspass.features.updateBusLocation.domain

import com.google.gson.annotations.SerializedName

data class BusLocationDto(
    @SerializedName("bus_number")
    val bus_number: String?,
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("longitude")
    val longitude: String?
)