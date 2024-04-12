package com.ram.buspass.features.locationView.data

import com.ram.buspass.features.locationView.domain.LocationViewRepository
import com.ram.buspass.helper.resource.remote.api.ApiService

class LocationViewRepoImpl(private val apiService: ApiService): LocationViewRepository {
    override suspend fun getLocationView(): LocationViewPojo {
        try {
            return apiService.getLocationView()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}