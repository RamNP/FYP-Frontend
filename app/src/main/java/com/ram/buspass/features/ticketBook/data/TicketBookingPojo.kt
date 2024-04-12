package com.ram.buspass.features.ticketBook.data

import com.google.gson.annotations.SerializedName

data class TicketBookingPojo(
	@SerializedName("message")
	val message: String? = null,
	@SerializedName("is_success")
	val isSuccess: Boolean? = null,
	@SerializedName("status")
	val status: Int? = null
)

