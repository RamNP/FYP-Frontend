package com.ram.buspass.helper.resource.remote.api.model.booTicketDetails

data class DataItem(
	val busDetails: BusDetails? = null,
	val ticketDetails: TicketDetails? = null,
	val bookDetails: BookDetails? = null
)
data class BusDetails(
	val fromTo: String? = null,
	val route: String? = null,
	val busSpeed: String? = null,
	val name: String? = null,
	val id: Int? = null,
	val busNumber: String? = null
)

data class BookingPojo(
	val data: List<DataItem>? = null,
	val message: String? = null,
	val isSuccess: Boolean? = null,
	val status: Int? = null
)

data class BookDetails(
	val bus: Int? = null,
	val ticket: Int? = null,
	val id: Int? = null,
	val bookDate: String? = null,
	val users: Int? = null
)

data class TicketDetails(
	val date: String? = null,
	val bus: Int? = null,
	val cost: Int? = null,
	val ticketNo: Int? = null,
	val id: Int? = null,
	val time: String? = null,
	val users: Int? = null
)

