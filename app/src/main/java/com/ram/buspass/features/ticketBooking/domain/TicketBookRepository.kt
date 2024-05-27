package com.ram.buspass.features.ticketBooking.domain

import com.ram.buspass.features.ticketBooking.data.TicketBookPojo
import com.ram.buspass.features.ticketBooking.data.TicketBookingPojo

interface TicketBookRepository {

    suspend fun getTicket(): TicketBookPojo?

    suspend fun getBookDetails(bookDto: BookDto): TicketBookingPojo?


}