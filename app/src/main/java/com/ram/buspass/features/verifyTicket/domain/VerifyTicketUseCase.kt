package com.ram.buspass.features.verifyTicket.domain

import com.ram.buspass.utils.Resource
import com.ram.buspass.features.verifyTicket.data.VerifyTicketPojo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VerifyTicketUseCase(private val verifyTicketRepository: VerifyTicketRepository) {

    operator fun invoke(): Flow<Resource<VerifyTicketPojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = verifyTicketRepository.getVerifyTicketDetails()))

        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }

    }
}