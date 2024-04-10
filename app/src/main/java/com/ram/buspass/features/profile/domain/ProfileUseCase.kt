package com.ram.buspass.features.profile.domain

import com.ram.buspass.helper.Resource
import com.ram.buspass.helper.resource.remote.api.model.profile.ProfilePojo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProfileUseCase(private val profileRepository: ProfileRepository) {

    operator fun invoke(): Flow<Resource<ProfilePojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = profileRepository.getUserProfile()))

        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }

    }
}