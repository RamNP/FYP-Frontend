package com.ram.buspass.features.locationView.presenation

import com.ram.buspass.features.locationView.data.LocationViewPojo

data class LocationViewState(
    val isLoading: Boolean  = false,
    val isData: LocationViewPojo? = null,
    val isError: String? = null
)