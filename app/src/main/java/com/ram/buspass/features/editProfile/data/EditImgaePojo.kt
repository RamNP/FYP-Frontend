package com.ram.buspass.features.editProfile.data

data class EditImgaePojo(
	val userProfile: UserProfile? = null,
	val message: String? = null,
	val isSuccess: Boolean? = null,
	val status: Int? = null
)

data class UserProfile(
	val role: String? = null,
	val address: String? = null,
	val isActive: Boolean? = null,
	val isSuperuser: Boolean? = null,
	val userPermissions: List<Any?>? = null,
	val isStaff: Boolean? = null,
	val lastLogin: Any? = null,
	val groups: List<Any?>? = null,
	val photoImage: String? = null,
	val password: String? = null,
	val id: Int? = null,
	val dateJoined: String? = null,
	val email: String? = null,
	val username: String? = null
)

