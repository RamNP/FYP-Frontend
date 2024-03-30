package com.ram.buspass.features.ticketBook.data

import com.ram.buspass.features.helper.resource.remote.api.ApiService
import com.ram.buspass.features.helper.resource.remote.api.model.booTicketDetails.BookPojo
import com.ram.buspass.features.helper.resource.remote.api.model.ticketBook.TicketBookPojo
import com.ram.buspass.features.ticketBook.domain.TicketBookRepository


class TicketBookRepositoryImpl(private val apiService: ApiService) : TicketBookRepository {
    override suspend fun getTicket(): TicketBookPojo {
        try {
            return apiService.getBookTicket()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    override suspend fun getBookDetails(): BookPojo {
        try {
            return apiService.getBookDetails()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

}