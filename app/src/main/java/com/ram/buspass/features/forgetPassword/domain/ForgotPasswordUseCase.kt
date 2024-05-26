package com.ram.buspass.features.forgetPassword.domain

import com.ram.buspass.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ForgotPasswordUseCase(private val forgotPasswordRepository: ForgotPasswordRepository) {

    operator fun invoke(email: String?, password: String?): Flow<Resource<ForgotPasswordPoJo?>> = flow {
        emit(Resource.Loading())
        try {
            val result = forgotPasswordRepository.getUpdatePassword(email, password)
            if (result?.is_success == true){
                emit(Resource.Success(data = result))
            } else {
                emit(Resource.Error(message = result?.message))
            }
        } catch (e: Exception) {
            emit(Resource.Error(message = "Not found!"))
        }
    }
}