package com.ram.buspass.features.bookingDetails.presentation

import com.ram.buspass.features.bookTicket.data.BookingPojo

data class BookingState(
    val isLoading: Boolean  = false,
    val isData: BookingPojo? = null,
    val isError: String? = null
)