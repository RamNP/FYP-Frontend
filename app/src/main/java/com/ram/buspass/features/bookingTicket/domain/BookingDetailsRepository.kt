package com.ram.buspass.features.bookingTicket.domain

import com.ram.buspass.features.bookingTicket.data.BookingPojo

 interface  BookingDetailsRepository {
    suspend fun getBookUserDetails(): BookingPojo
}