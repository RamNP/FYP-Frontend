package com.ram.buspass.features.ticketBook.presentation

import com.ram.buspass.features.helper.resource.remote.api.model.ticketBook.DataItem

data class TicketBookState(
    val isLoading: Boolean  = false,
    val isData: List<DataItem>? = null,
    val isError: String = ""
)