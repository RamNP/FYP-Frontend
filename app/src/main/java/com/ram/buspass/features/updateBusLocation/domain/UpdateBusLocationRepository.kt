package com.ram.buspass.features.updateBusLocation.domain

import com.ram.buspass.features.updateBusLocation.data.LocationPojo

interface UpdateBusLocationRepository {

    suspend fun getBusLocation(busNumber: String? = null,latitude: String? = null, longitude: String? = null): LocationPojo?
}