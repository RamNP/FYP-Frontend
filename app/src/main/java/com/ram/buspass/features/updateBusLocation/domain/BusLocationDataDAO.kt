package com.ram.buspass.features.updateBusLocation.domain

import com.google.gson.annotations.SerializedName

data class BusLocationDataDAO(
    @SerializedName("bus_number")
    val busNumber: String?,
    @SerializedName("latitude")
    val latitude: Float?,
    @SerializedName("longitude")
    val longitude: Float?
)