package com.ram.buspass.features.updateBusLocation.data

import com.ram.buspass.features.updateBusLocation.domain.BusLocationDto
import com.ram.buspass.features.updateBusLocation.domain.UpdateBusLocationRepository
import com.ram.buspass.helper.resource.remote.api.ApiService

class UpdateBusLocationRepoImpl (private val apiService: ApiService):UpdateBusLocationRepository{
    override suspend fun getBusLocation(busLocationDto: BusLocationDto): LocationPojo? {
        try {
            return apiService.updateBusLocation(busLocationDto)

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