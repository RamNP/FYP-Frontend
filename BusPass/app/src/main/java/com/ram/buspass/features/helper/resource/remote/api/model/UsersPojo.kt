package com.edu.vplayer.features.data.resource.remote.api.model

data class UsersPojo(
	val result: ResultItem? = null,
	val success: Boolean? = null,
	val abp: Boolean? = null,
	val error: Any? = null,
	val targetUrl: Any? = null,
	val unAuthorizedRequest: Boolean? = null
)
data class ResultItem(
	val pendingBalance: String? = null,
	val isSchoolChatroomEnabled: Boolean? = null,
	val role: String,
	val isActive: Boolean? = null,
	val countryId: Int? = null,
	val schoolPhotoUrl: String,
	val isPasswordEmpty: Boolean? = null,
	val photoUrl: String? = null,
	val createdAt: String? = null,
	val emailAddress: String,
	val userMode: String? = null,
	val isB2C: Boolean? = null,
	val nickname: String? = null,
	val id: Int? = null,
	val schoolName: String,
	val isEBookPrintFeatureEnabled: Boolean? = null,
	val isLite: Boolean? = null,
	val gradeId: Int? = null,
	val tutorCreditBalance: Any? = null,
	val heading: String? = null,
	val subjects: Any? = null,
	val fullName: String,
	val userId: Int,
	val isOtpEnabled: Boolean? = null,
	val phoneNumber: String,
	val grade: String,
	val location: String,
	val isEmailConfirmed: Boolean? = null
)
