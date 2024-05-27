package com.ram.buspass.features.editProfile.data

import android.util.Log
import com.ram.buspass.features.editProfile.domain.EditModelDto
import com.ram.buspass.features.editProfile.domain.EditProfileRepository
import com.ram.buspass.helper.resource.remote.api.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class EditProfileRepoImpl (private val apiService: ApiService):EditProfileRepository{
    override suspend fun getEditUserProfile(userId: Int? ,username: String? , address: String? ): EditProfilePojo? {
        val editProfileDetails = EditModelDto(userId ,username , address)
        return  apiService.editUserProfile(editProfileDetails)
    }

    override suspend fun updateProfileImage(userId: Int?, imageFile: File?): EditImagePojo? {
        val requestFile = imageFile?.asRequestBody("image/*".toMediaTypeOrNull())
        val image = requestFile.let { MultipartBody.Part.createFormData("image", imageFile?.name ?: "No food", requestFile ?: error("Food's image is not found")) }
        val userIdRequestBody = userId.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        Log.e("UserId" ,"$userId")
        return apiService.editProfileImage(userIdRequestBody, image)
    }
}

