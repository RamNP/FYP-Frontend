package com.ram.buspass.features.verifyTicket.presentation

import com.ram.buspass.features.passVerify.data.PassVerifyPojo

data class VerifyTicketState(
    val isLoading: Boolean  = false,
    val isData: PassVerifyPojo? = null,
    val isError: String? = null
)