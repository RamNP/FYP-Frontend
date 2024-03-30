package com.ram.buspass.features.ticketBook.presentation

import com.ram.buspass.features.helper.resource.remote.api.model.booTicketDetails.DataItems

data class BookTicketState(
    val isLoading: Boolean  = false,
    val isData: List<DataItems>? = null,
    val isError: String = ""
)