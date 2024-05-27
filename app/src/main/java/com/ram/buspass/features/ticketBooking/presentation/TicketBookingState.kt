package com.ram.buspass.features.ticketBooking.presentation

import com.ram.buspass.features.ticketBooking.data.TicketBookingPojo

data class TicketBookingState(
    val isLoading: Boolean  = false,
    val isData: TicketBookingPojo? = null,
    val isError: String? = null
)