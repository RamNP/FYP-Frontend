package com.ram.buspass.features.ticketBooking.data

import com.ram.buspass.features.ticketBooking.domain.BookDto
import com.ram.buspass.features.ticketBooking.domain.TicketBookRepository
import com.ram.buspass.helper.resource.remote.api.ApiService


class TicketBookRepositoryImpl(private val apiService: ApiService) : TicketBookRepository {
    override suspend fun getTicket(): TicketBookPojo {
        try {
            return apiService.getBookTicket()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    override suspend fun getBookDetails(bookDto: BookDto): TicketBookingPojo {
        try {
            return apiService.getBookDetails(bookDto)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

}