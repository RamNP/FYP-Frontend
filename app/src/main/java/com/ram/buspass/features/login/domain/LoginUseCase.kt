package com.ram.buspass.features.login.domain

import com.ram.buspass.features.login.data.LoginPojo
import com.ram.buspass.utils.Resource
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
            emit(Resource.Error(message = "Not Found!"))
        }
    }
}