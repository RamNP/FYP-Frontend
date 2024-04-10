package com.ram.buspass.features.busLocationView.domain

import com.ram.buspass.helper.Resource
import com.ram.buspass.helper.resource.remote.api.model.verifyTicket.VerifyTicketPojo
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