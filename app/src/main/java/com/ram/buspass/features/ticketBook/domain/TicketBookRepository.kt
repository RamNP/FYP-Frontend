package com.ram.buspass.features.ticketBook.domain

import com.ram.buspass.features.ticketBook.data.TicketBookPojo
import com.ram.buspass.features.ticketBook.data.TicketBookingPojo

interface TicketBookRepository {

    suspend fun getTicket(): TicketBookPojo?

    suspend fun getBookDetails(bookDto: BookDto): TicketBookingPojo?


}