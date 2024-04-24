package com.ram.buspass.features.editProfile.domain

import com.ram.buspass.features.editProfile.data.EditImagePojo
import com.ram.buspass.features.editProfile.data.EditProfilePojo
import com.ram.buspass.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

class EditProfileUseCase(private val editProfileRepository: EditProfileRepository) {

    operator fun invoke(email: String? = null,username: String? = null, ): Flow<Resource<EditProfilePojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(editProfileRepository.getEditUserProfile(email, username, )))
        } catch (e: Exception){
            emit(Resource.Error(message = "Not found!"))
        }
    }

    operator fun invoke(userId: Int?, imageFile: File?): Flow<Resource<EditImagePojo?>> = flow {
        emit(Resource.Loading())
        try {
            val result = editProfileRepository.updateProfileImage(userId, imageFile)
            if (result?.is_success == true){
                emit(Resource.Success(data = result))
            } else {
                emit(Resource.Error(message  = result?.message))
            }
        } catch (e: Exception) {
            emit(Resource.Error(message = "Not found!"))
        }
    }

}