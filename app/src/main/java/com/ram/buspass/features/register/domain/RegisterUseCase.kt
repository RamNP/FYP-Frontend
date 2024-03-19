package com.ram.buspass.features.register.domain

import com.ram.buspass.features.data.common.Resource
import com.ram.buspass.features.helper.resource.remote.api.model.ResponsePojo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUseCase(private val registerRepository: RegisterRepository) {
    operator fun invoke(email: String? = null, password: String? = null, role: String? = null): Flow<Resource<ResponsePojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(registerRepository.getRegisterUser(email, password, role)))
        } catch (e: Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}