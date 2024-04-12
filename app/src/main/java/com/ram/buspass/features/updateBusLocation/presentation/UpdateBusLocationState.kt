package com.ram.buspass.features.updateBusLocation.presentation

import com.ram.buspass.features.updateBusLocation.data.LocationPojo

data class UpdateBusLocationState(
    val isLoading: Boolean  = false,
    val isData: LocationPojo? = null,
    val isError: String = ""
)