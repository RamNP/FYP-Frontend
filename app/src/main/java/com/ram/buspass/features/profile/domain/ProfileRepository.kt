package com.ram.buspass.features.profile.domain

import com.ram.buspass.helper.resource.remote.api.model.profile.ProfilePojo

interface  ProfileRepository {
    suspend fun getUserProfile(): ProfilePojo
}