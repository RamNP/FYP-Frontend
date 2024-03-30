package com.ram.buspass.features.ticketBook.domain

import com.ram.buspass.features.helper.Resource
import com.ram.buspass.features.helper.resource.remote.api.model.ticketBook.TicketBookPojo
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

//    operator fun invoke(data): Flow<Resource<BookPojo?>> = flow {
//        emit(Resource.Loading())
//        try {
//            emit(Resource.Success(ticketBookRepository.getBookDetails(dcsd)))
//        } catch (e: Exception){
//            emit(Resource.Error(e.message.toString()))
//        }
//    }


}