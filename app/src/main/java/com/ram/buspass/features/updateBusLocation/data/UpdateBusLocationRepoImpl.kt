package com.ram.buspass.features.updateBusLocation.data

import com.ram.buspass.features.updateBusLocation.domain.BusLocationDataDAO
import com.ram.buspass.features.updateBusLocation.domain.UpdateBusLocationRepository
import com.ram.buspass.helper.resource.remote.api.ApiService
import com.ram.buspass.helper.resource.remote.api.model.updateBusLocation.LocationPojo

class UpdateBusLocationRepoImpl (private val apiService: ApiService):UpdateBusLocationRepository{
    override suspend fun getBusLocation(busNumber: String?, latitude: Float?, longitude: Float?
    ): LocationPojo? {
        val locationDetails = BusLocationDataDAO(busNumber ,latitude ,longitude)
        return apiService.updateBusLocation(locationDetails)
    }
}