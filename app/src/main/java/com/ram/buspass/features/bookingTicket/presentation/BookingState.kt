package com.ram.buspass.features.bookingTicket.presentation

import com.ram.buspass.features.bookingTicket.data.BookingPojo

data class BookingState(
    val isLoading: Boolean  = false,
    val isData: BookingPojo? = null,
    val isError: String? = null
)