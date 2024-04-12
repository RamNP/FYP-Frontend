package com.ram.buspass.features.locationView.domain

import com.ram.buspass.features.locationView.data.LocationViewPojo

interface  LocationViewRepository {
    suspend fun getLocationView(): LocationViewPojo
}