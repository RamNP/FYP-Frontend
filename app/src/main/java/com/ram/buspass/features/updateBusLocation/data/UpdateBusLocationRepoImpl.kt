package com.ram.buspass.features.updateBusLocation.data

import com.ram.buspass.features.updateBusLocation.domain.BusLocationDto
import com.ram.buspass.features.updateBusLocation.domain.UpdateBusLocationRepository
import com.ram.buspass.helper.resource.remote.api.ApiService

class UpdateBusLocationRepoImpl (private val apiService: ApiService):UpdateBusLocationRepository{
    override suspend fun getBusLocation(busNumber: String?, latitude: String?, longitude: String?
    ): LocationPojo? {
        try {
            val locationDetails = BusLocationDto(busNumber ,latitude ,longitude)
            return apiService.updateBusLocation(locationDetails)

        } catch (e: Exception){
            throw Exception(e)

        }

    }
    override suspend fun getBooingBus(): BookingBusPojo? {
        try {
            return apiService.getBookingBus()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}