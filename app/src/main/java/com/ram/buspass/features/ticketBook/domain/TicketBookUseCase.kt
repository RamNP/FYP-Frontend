package com.ram.buspass.features.ticketBook.domain

import com.ram.buspass.utils.Resource
import com.ram.buspass.features.ticketBook.data.TicketBookPojo
import com.ram.buspass.features.ticketBook.data.TicketBookingPojo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TicketBookUseCase(private val ticketBookRepository: TicketBookRepository) {

    operator fun invoke(): Flow<Resource<TicketBookPojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = ticketBookRepository.getTicket()))

        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
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
            emit(Resource.Error(e.message.toString()))
        }
    }


}