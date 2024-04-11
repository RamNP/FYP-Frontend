package com.ram.buspass.features.chanagePassword.presentation

import com.ram.buspass.helper.resource.remote.api.model.updateBusLocation.LocationPojo

data class ChangePasswordState(
    val isLoading: Boolean  = false,
    val isData: LocationPojo? = null,
    val isError: String = ""
)