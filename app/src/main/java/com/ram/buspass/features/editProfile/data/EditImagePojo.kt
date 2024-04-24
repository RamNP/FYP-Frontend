package com.ram.buspass.features.editProfile.data

data class EditImagePojo(
	val user_profile: UserProfile? = null,
	val message: String? = null,
	val is_success: Boolean? = null,
	val status: Int? = null
)

data class UserProfile(
	val role: String? = null,
	val address: String? = null,
	val is_active: Boolean? = null,
	val is_superuser: Boolean? = null,
	val user_permissions: List<Any?>? = null,
	val is_staff: Boolean? = null,
	val last_login: Any? = null,
	val groups: List<Any?>? = null,
	val photo_image: String? = null,
	val password: String? = null,
	val id: Int? = null,
	val date_joined: String? = null,
	val email: String? = null,
	val username: String? = null
)

