package com.ram.buspass.features.verifyTicket.domain

import com.ram.buspass.features.passVerify.data.PassVerifyPojo

interface  VerifyTicketRepository {
    suspend fun getVerifyTicketDetails(): PassVerifyPojo
}