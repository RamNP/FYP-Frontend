package com.ram.buspass.features.ticketBook.presentation

import com.ram.buspass.features.ticketBook.data.TicketBookingPojo

data class TicketBookingState(
    val isLoading: Boolean  = false,
    val isData: TicketBookingPojo? = null,
    val isError: String? = null
)