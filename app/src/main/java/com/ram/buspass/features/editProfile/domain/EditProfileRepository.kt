package com.ram.buspass.features.editProfile.domain

import com.ram.buspass.features.editProfile.data.EditProfilePojo

interface EditProfileRepository {

    suspend fun getEditUserProfile(email: String? = null,username: String? = null,): EditProfilePojo?
}