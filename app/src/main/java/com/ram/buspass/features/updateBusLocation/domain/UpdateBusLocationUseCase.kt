package com.ram.buspass.features.updateBusLocation.domain

import com.ram.buspass.helper.Resource
import com.ram.buspass.helper.resource.remote.api.model.updateBusLocation.LocationPojo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateBusLocationUseCase(private val updateBusLocationRepository: UpdateBusLocationRepository) {

    operator fun invoke(busNumber: String? = null,latitude: Float? = null, longitude: Float? = null): Flow<Resource<LocationPojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(updateBusLocationRepository.getBusLocation(busNumber, latitude, longitude)))
        } catch (e: Exception){
            emit(Resource.Error(message = "Not found!"))
        }
    }
}