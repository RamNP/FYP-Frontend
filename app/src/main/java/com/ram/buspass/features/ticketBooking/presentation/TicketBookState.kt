package com.ram.buspass.features.ticketBooking.presentation

import com.ram.buspass.features.ticketBooking.data.TicketBookPojo

data class TicketBookState(
    val isLoading: Boolean  = false,
    val isData: TicketBookPojo? = null,
    val isError: String? = null
)