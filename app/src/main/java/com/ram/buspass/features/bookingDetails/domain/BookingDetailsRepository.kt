package com.ram.buspass.features.bookingDetails.domain

import com.ram.buspass.helper.resource.remote.api.model.booTicketDetails.BookingPojo

 interface  BookingDetailsRepository {
    suspend fun getBookUserDetails():BookingPojo
}