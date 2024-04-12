package com.ram.buspass.helper.resource.remote.api.model.passVerify


data class PassVerifyPojo(
	val data: List<DataItem?>? = null,
	val message: String? = null,
	val is_success: Boolean? = null,
	val status: Int? = null
)

data class UserDetails(
	val role: String? = null,
	val address: String? = null,
	val is_active: Boolean? = null,
	val is_superuser: Boolean? = null,
	val user_permissions: List<Any?>? = null,
	val is_staff: Boolean? = null,
	val last_login: Any? = null,
	val groups: List<Any?>? = null,
	val photo_image: Any? = null,
	val password: String? = null,
	val id: Int? = null,
	val date_joined: String? = null,
	val email: String? = null,
	val username: String? = null
)

data class DataItem(
	val pass_details: PassDetails? = null,
	val bus_details: BusDetails? = null,
	val ticket_details: TicketDetails? = null,
	val user_details: UserDetails? = null
)

data class BusDetails(
	val from_to: String? = null,
	val route: String? = null,
	val bus_speed: String? = null,
	val name: String? = null,
	val id: Int? = null,
	val bus_number: String? = null
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

data class PassDetails(
	val bus: Int? = null,
	val ticket: Int? = null,
	val id: Int? = null,
	val created_date: String? = null,
	val user: Int? = null,
	val status: String? = null
)

