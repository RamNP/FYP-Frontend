package com.ram.buspass.features.bookingDetails.data

import com.ram.buspass.features.bookingDetails.domain.BookingDetailsRepository
import com.ram.buspass.helper.resource.remote.api.ApiService
import com.ram.buspass.helper.resource.remote.api.model.booTicketDetails.BookingPojo

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