package com.ram.buspass.features.register.domain

import com.ram.buspass.utils.Resource
import com.ram.buspass.features.register.data.ResponsePojo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

    class RegisterUseCase(private val registerRepository: RegisterRepository) {
    operator fun invoke(email: String? = null, username:String? ,password: String? = null, role: String? = null): Flow<Resource<ResponsePojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(registerRepository.getRegisterUser(email,username, password, role)))
        } catch (e: Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}