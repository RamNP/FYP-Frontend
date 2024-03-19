package com.ram.buspass.features.ticketBook.data

import com.ram.buspass.features.helper.resource.remote.api.ApiService
import com.ram.buspass.features.helper.resource.remote.api.model.ticketBook.TicketBookPojo
import com.ram.buspass.features.ticketBook.domain.TicketBookRepository


class TicketBookRepositoryImpl (private val apiService: ApiService): TicketBookRepository {
    override suspend fun getTicket(busId: Int?): TicketBookPojo {
        try {
            return apiService.getBookTicket(busId)
        }catch (e: Exception)
        {
            throw Exception (e)
        }
    }

}