package com.ram.buspass.features.verifyTicket.data

import com.ram.buspass.features.verifyTicket.domain.VerifyTicketRepository
import com.ram.buspass.helper.resource.remote.api.ApiService
import com.ram.buspass.helper.resource.remote.api.model.verifyTicket.VerifyTicketPojo

class VerifyTicketRepoImpl(private val apiService: ApiService) : VerifyTicketRepository {
    override suspend fun getVerifyTicketDetails(): VerifyTicketPojo {
        try {
            return apiService.getVerifyTicket()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}