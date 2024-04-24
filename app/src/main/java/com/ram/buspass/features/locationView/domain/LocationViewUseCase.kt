package com.ram.buspass.features.locationView.domain

import com.ram.buspass.features.locationView.data.LocationViewPojo
import com.ram.buspass.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocationViewUseCase(private  val locationViewRepository: LocationViewRepository) {


    operator fun invoke(): Flow<Resource<LocationViewPojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = locationViewRepository.getLocationView()))

        } catch (e: Exception) {
            emit(Resource.Error(message = "No Fund!"))
        }

    }
}