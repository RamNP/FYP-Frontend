package com.ram.buspass.features.updateBusLocation.presentation

import com.ram.buspass.helper.resource.remote.api.model.updateBusLocation.LocationPojo

data class UpdateBusLocationState(
    val isLoading: Boolean  = false,
    val isData: LocationPojo? = null,
    val isError: String = ""
)