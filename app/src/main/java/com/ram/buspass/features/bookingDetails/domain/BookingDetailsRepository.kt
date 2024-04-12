package com.ram.buspass.features.bookingDetails.domain

import com.ram.buspass.features.bookTicket.data.BookingPojo

 interface  BookingDetailsRepository {
    suspend fun getBookUserDetails(): BookingPojo
}