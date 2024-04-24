package com.ram.buspass.features.register.domain

import com.ram.buspass.features.register.data.RegisterPojo
import com.ram.buspass.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUseCase(private val registerRepository: RegisterRepository) {
    operator fun invoke(email: String?, username:String? ,password: String?, role: String?): Flow<Resource<RegisterPojo>> = flow {
        emit(Resource.Loading())
        try {
            val response = registerRepository.getRegisterUser(email,username, password, role)
            if (response?.isSuccess == true){
                emit(Resource.Success(data = response))
            } else {
                emit(Resource.Error(message = response?.message))
            }
        } catch (e: Exception){
            emit(Resource.Error("Not Found!"))
        }
    }
}