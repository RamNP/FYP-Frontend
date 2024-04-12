package com.ram.buspass.features.ticketBook.presentation

import com.ram.buspass.features.ticketBook.data.TicketBookPojo

data class TicketBookState(
    val isLoading: Boolean  = false,
    val isData: TicketBookPojo? = null,
    val isError: String? = null
)