package com.ram.buspass.features.verifyTicket.presentation

import com.ram.buspass.features.verifyTicket.data.VerifyTicketPojo

data class VerifyTicketState(
    val isLoading: Boolean  = false,
    val isData: VerifyTicketPojo? = null,
    val isError: String? = null
)