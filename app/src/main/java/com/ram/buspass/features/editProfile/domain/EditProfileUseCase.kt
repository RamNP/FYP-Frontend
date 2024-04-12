package com.ram.buspass.features.editProfile.domain

import com.ram.buspass.helper.Resource
import com.ram.buspass.helper.resource.remote.api.model.editProfile.EditProfilePojo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EditProfileUseCase(private val editProfileRepository: EditProfileRepository) {

    operator fun invoke(email: String? = null,username: String? = null, ): Flow<Resource<EditProfilePojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(editProfileRepository.getEditUserProfile(email, username, )))
        } catch (e: Exception){
            emit(Resource.Error(message = "Not found!"))
        }
    }
}