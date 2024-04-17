package com.ram.buspass.features.updateBusLocation.presentation

import com.ram.buspass.features.updateBusLocation.data.BookingBusPojo

data class BookingBusState(
    val isLoading: Boolean  = false,
    val isData: BookingBusPojo? = null,
    val isError: String? = null
)