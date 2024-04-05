package com.ram.buspass.features.ticketBook.presentation

import com.ram.buspass.helper.resource.remote.api.model.ticketBook.TicketBookPojo

data class TicketBookState(
    val isLoading: Boolean  = false,
    val isData: TicketBookPojo? = null,
    val isError: String? = null
)