package com.ram.buspass.features.updateBusLocation.domain

import com.ram.buspass.features.updateBusLocation.data.BookingBusPojo
import com.ram.buspass.features.updateBusLocation.data.LocationPojo

interface UpdateBusLocationRepository {

    suspend fun getBusLocation(busLocationDto: BusLocationDto): LocationPojo?

    suspend fun getBooingBus(): BookingBusPojo?



}