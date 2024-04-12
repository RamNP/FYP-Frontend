package com.ram.buspass.features.verifyTicket.data

data class VerifyTicketPojo(
	val data: List<DataItem?>? = null,
	val message: String? = null,
	val is_success: Boolean? = null,
	val status: Int? = null
)

data class TicketDetails(
	val date: String? = null,
	val bus: Int? = null,
	val cost: Int? = null,
	val ticket_no: Int? = null,
	val id: Int? = null,
	val time: String? = null,
	val users: Int? = null
)

data class BusDetails(
	val from_to: String? = null,
	val route: String? = null,
	val bus_speed: String? = null,
	val name: String? = null,
	val id: Int? = null,
	val bus_number: String? = null
)

data class DataItem(
	val bus_details: BusDetails? = null,
	val ticket_details: TicketDetails? = null
)

