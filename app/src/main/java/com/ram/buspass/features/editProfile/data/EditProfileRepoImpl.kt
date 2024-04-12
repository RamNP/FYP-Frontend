package com.ram.buspass.features.editProfile.data

import com.ram.buspass.features.editProfile.domain.EditModelDto
import com.ram.buspass.features.editProfile.domain.EditProfileRepository
import com.ram.buspass.helper.resource.remote.api.ApiService

class EditProfileRepoImpl (private val apiService: ApiService):EditProfileRepository{
    override suspend fun getEditUserProfile(email: String?, username: String?, ): EditProfilePojo? {
        val editProfileDetails = EditModelDto(email ,username )
        return  apiService.editUserProfile(editProfileDetails)
    }
}

