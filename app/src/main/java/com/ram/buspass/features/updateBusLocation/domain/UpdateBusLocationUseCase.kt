package com.ram.buspass.features.updateBusLocation.domain

import android.util.Log
import com.ram.buspass.features.updateBusLocation.data.BookingBusPojo
import com.ram.buspass.features.updateBusLocation.data.LocationPojo
import com.ram.buspass.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateBusLocationUseCase(private val updateBusLocationRepository: UpdateBusLocationRepository) {

    operator fun invoke(busLocationDto: BusLocationDto): Flow<Resource<LocationPojo?>> = flow {
        emit(Resource.Loading())
        try {
            Log.e("BusLocationDto" ,"$busLocationDto")


            emit(Resource.Success(updateBusLocationRepository.getBusLocation(busLocationDto)))
        } catch (e: Exception){
            emit(Resource.Error(message = ""))
        }
    }


    operator fun invoke(): Flow<Resource<BookingBusPojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(updateBusLocationRepository.getBooingBus()))
        } catch (e: Exception){
            emit(Resource.Error(message = "Not found!"))
        }
    }
}