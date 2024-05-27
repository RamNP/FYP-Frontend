package com.ram.buspass.features.ticketBooking.domain

import com.ram.buspass.features.ticketBooking.data.TicketBookPojo
import com.ram.buspass.features.ticketBooking.data.TicketBookingPojo
import com.ram.buspass.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TicketBookUseCase(private val ticketBookRepository: TicketBookRepository) {

    operator fun invoke(): Flow<Resource<TicketBookPojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = ticketBookRepository.getTicket()))

        } catch (e: Exception) {
            emit(Resource.Error(message =" No Fund!"))
        }

    }

    operator fun invoke(bookDto: BookDto): Flow<Resource<TicketBookingPojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(
                Resource.Success(
                    ticketBookRepository.getBookDetails(bookDto)
                )
            )
        } catch (e: Exception) {
            emit(Resource.Error("No Fund!"))
        }
    }


}