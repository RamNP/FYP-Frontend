package com.ram.buspass.features.verifyTicket.data

import com.ram.buspass.features.passVerify.data.PassVerifyPojo
import com.ram.buspass.features.verifyTicket.domain.VerifyTicketRepository
import com.ram.buspass.helper.resource.remote.api.ApiService

class VerifyTicketRepoImpl(private val apiService: ApiService) : VerifyTicketRepository {
    override suspend fun getVerifyTicketDetails(): PassVerifyPojo {
        try {
            return apiService.getVerifyTicket()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}