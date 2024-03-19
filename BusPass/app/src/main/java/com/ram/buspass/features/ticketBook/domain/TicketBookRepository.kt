package com.ram.buspass.features.ticketBook.domain

import com.ram.buspass.features.helper.resource.remote.api.model.ticketBook.TicketBookPojo

interface TicketBookRepository {

    suspend fun getTicket(busId: Int?):TicketBookPojo
}