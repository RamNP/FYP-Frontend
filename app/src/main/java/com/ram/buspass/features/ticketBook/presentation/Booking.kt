package com.ram.buspass.features.ticketBook.presentation

data class Booking(
    val busNumber: String? = null,
    val busName: String? = null,
    val fromTo: String? = null,
    val busRoute: String? = null,
    val ticketNum: Int? = null,
    val ticketDate: String? = null,
    val ticketTime: String? = null,
    val ticketCost: Int? = null
)
