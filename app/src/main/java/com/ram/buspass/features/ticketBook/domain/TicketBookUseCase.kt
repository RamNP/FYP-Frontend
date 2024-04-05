package com.ram.buspass.features.ticketBook.domain

import com.ram.buspass.helper.Resource
import com.ram.buspass.helper.resource.remote.api.model.ticketBook.TicketBookPojo
import com.ram.buspass.helper.resource.remote.api.model.ticketBook.TicketBookingPojo
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