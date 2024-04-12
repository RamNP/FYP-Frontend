package com.ram.buspass.features.verifyTicket.presentation

import com.ram.buspass.helper.resource.remote.api.model.verifyTicket.VerifyTicketPojo

data class VerifyTicketState(
    val isLoading: Boolean  = false,
    val isData: VerifyTicketPojo? = null,
    val isError: String? = null
)