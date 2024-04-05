package com.ram.buspass.features.ticketBook.domain

import com.ram.buspass.helper.resource.remote.api.model.ticketBook.TicketBookPojo
import com.ram.buspass.helper.resource.remote.api.model.ticketBook.TicketBookingPojo

interface TicketBookRepository {

    suspend fun getTicket(): TicketBookPojo?

    suspend fun getBookDetails(bookDto: BookDto): TicketBookingPojo?


}