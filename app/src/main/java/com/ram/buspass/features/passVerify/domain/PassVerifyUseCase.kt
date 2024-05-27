package com.ram.buspass.features.passVerify.domain

import com.ram.buspass.features.passVerify.data.PassVerifyPojo
import com.ram.buspass.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PassVerifyUseCase(private  val passVerifyRepository: PassVerifyRepository) {

    operator fun invoke(): Flow<Resource<PassVerifyPojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(passVerifyRepository.getVerifyPass()))
        } catch (e: Exception){
            emit(Resource.Error(message = ""))
        }
    }

    operator fun invoke(passId: Int?): Flow<Resource<PassVerifyPojo>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = passVerifyRepository.getPassStatusApproved(passId)))
        } catch (e: Exception) {
            emit(Resource.Error(message = ""))
        }
    }

}