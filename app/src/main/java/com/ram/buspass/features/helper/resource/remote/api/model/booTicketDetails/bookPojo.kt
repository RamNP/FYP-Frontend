package com.ram.buspass.features.helper.resource.remote.api.model.booTicketDetails

data class DataItems(
	val busDetails: BusDetails? = null,
	val ticketDetails: TicketDetails? = null
)

data class BusDetails(
	val fromTo: String? = null,
	val route: String? = null,
	val busSpeed: String? = null,
	val name: String? = null,
	val id: Int? = null,
	val busNumber: String? = null
)

data class BookPojo(
	val data: List<DataItems>? = null,
	val message: String? = null
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

