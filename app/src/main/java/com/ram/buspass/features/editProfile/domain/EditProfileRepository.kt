package com.ram.buspass.features.editProfile.domain

import com.ram.buspass.features.editProfile.data.EditImagePojo
import com.ram.buspass.features.editProfile.data.EditProfilePojo
import java.io.File

interface EditProfileRepository {
    suspend fun getEditUserProfile(userId: Int? ,username: String? = null, address: String? = null): EditProfilePojo?
    suspend fun updateProfileImage(userId: Int?, imageFile: File?): EditImagePojo?

}