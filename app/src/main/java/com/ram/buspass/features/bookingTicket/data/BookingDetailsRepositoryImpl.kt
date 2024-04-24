package com.ram.buspass.features.bookingTicket.data

import com.ram.buspass.features.bookingTicket.domain.BookingDetailsRepository
import com.ram.buspass.helper.resource.remote.api.ApiService

class BookingDetailsRepositoryImpl(private val apiService: ApiService): BookingDetailsRepository {
    override suspend fun getBookUserDetails(): BookingPojo {
        try {
            return apiService.getUserBooking()
        }catch (e: Exception)
        {
            throw Exception (e)
        }
    }

}