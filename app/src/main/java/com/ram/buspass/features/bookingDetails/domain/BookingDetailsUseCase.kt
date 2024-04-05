package com.ram.buspass.features.bookingDetails.domain

import com.ram.buspass.helper.Resource
import com.ram.buspass.helper.resource.remote.api.model.booTicketDetails.BookingPojo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookingDetailsUseCase(private val bookingDetailsRepository: BookingDetailsRepository) {

    operator fun invoke(): Flow<Resource<BookingPojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = bookingDetailsRepository.getBookUserDetails()))

        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }

    }
}