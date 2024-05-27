package com.ram.buspass.features.ticketBookingDetails.presentation

import com.ram.buspass.features.ticketBookingDetails.data.BookingPojo

data class BookingState(
    val isLoading: Boolean  = false,
    val isData: BookingPojo? = null,
    val isError: String? = null
)