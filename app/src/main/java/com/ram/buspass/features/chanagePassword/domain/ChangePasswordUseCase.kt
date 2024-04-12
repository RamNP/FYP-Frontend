package com.ram.buspass.features.chanagePassword.domain

import com.ram.buspass.utils.Resource
import com.ram.buspass.features.chanagePassword.data.ChangePasswordPojo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChangePasswordUseCase(private val changePasswordRepository: ChangePasswordRepository) {

    operator fun invoke(email: String? = null,oldPassword: String? = null, newPassword: String? = null): Flow<Resource<ChangePasswordPojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(changePasswordRepository.getPassword(email, oldPassword, newPassword)))
        } catch (e: Exception){
            emit(Resource.Error(message = "Not found!"))
        }
    }
}