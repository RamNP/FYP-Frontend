package com.ram.buspass.features.editProfile.domain

import com.ram.buspass.helper.resource.remote.api.model.editProfile.EditProfilePojo

interface EditProfileRepository {

    suspend fun getEditUserProfile(email: String? = null,username: String? = null,): EditProfilePojo?
}