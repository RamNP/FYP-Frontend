package com.ram.buspass.features.verifyTicket.domain

import com.ram.buspass.helper.resource.remote.api.model.verifyTicket.VerifyTicketPojo

interface  VerifyTicketRepository {
    suspend fun getVerifyTicketDetails(): VerifyTicketPojo
}