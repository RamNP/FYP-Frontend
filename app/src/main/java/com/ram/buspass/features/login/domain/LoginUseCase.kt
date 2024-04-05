package com.ram.buspass.features.login.domain

import com.ram.buspass.helper.Resource
import com.ram.buspass.helper.resource.remote.api.model.login.LoginPojo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase(private val loginRepository: LoginRepository) {
    operator fun invoke(email: String?, password: String?): Flow<Resource<LoginPojo?>> = flow {
        emit(Resource.Loading())
        try {
            val result = loginRepository.getLoginUser(email, password)
            if (result?.isSuccess == true){
                emit(Resource.Success(data = result))
            } else {
                emit(Resource.Error(message = result?.message))
            }
        } catch (e: Exception){
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}