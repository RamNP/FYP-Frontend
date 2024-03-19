package com.ram.buspass.googleMap.domain

import com.google.android.gms.maps.model.LatLng
import com.ram.buspass.googleMap.data.ILocationService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationService: ILocationService
) {
    operator fun invoke(): Flow<LatLng?> = locationService.requestLocationUpdates()

}