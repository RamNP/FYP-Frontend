package com.ram.buspass.features.ticketBook.domain

import com.ram.buspass.features.data.common.Resource
import com.ram.buspass.features.helper.resource.remote.api.model.ticketBook.TicketBookPojo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TicketBookUseCase(private val ticketBookRepository: TicketBookRepository) {

    operator fun invoke(busId: Int): Flow<Resource<TicketBookPojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = ticketBookRepository.getTicket(busId)))

        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }

    }
}