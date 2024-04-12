package com.ram.buspass.features.verifyTicket.domain

import com.ram.buspass.features.verifyTicket.data.VerifyTicketPojo

interface  VerifyTicketRepository {
    suspend fun getVerifyTicketDetails(): VerifyTicketPojo
}