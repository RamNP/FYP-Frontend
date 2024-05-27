package com.ram.buspass.features.ticketBookingDetails.domain

import com.ram.buspass.features.ticketBookingDetails.data.BookingPojo

 interface  BookingDetailsRepository {
    suspend fun getBookUserDetails(): BookingPojo
}