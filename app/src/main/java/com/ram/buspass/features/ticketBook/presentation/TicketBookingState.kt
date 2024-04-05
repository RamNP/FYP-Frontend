package com.ram.buspass.features.ticketBook.presentation

import com.ram.buspass.helper.resource.remote.api.model.ticketBook.TicketBookingPojo

data class TicketBookingState(
    val isLoading: Boolean  = false,
    val isData: TicketBookingPojo? = null,
    val isError: String? = null
)