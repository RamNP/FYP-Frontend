package com.ram.buspass.features.updateBusLocation.domain

import com.ram.buspass.features.updateBusLocation.data.LocationPojo

interface UpdateBusLocationRepository {

    suspend fun getBusLocation(busNumber: String? = null,latitude: Float? = null, longitude: Float? = null): LocationPojo?
}