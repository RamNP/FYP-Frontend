package com.ram.buspass.features.passVerify.domain

import com.ram.buspass.utils.Resource
import com.ram.buspass.features.passVerify.data.PassVerifyPojo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PassVerifyUseCase(private  val passVerifyRepository: PassVerifyRepository) {

    operator fun invoke(): Flow<Resource<PassVerifyPojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(passVerifyRepository.getVerifyPass()))
        } catch (e: Exception){
            emit(Resource.Error(message = "Not found!"))
        }
    }

}