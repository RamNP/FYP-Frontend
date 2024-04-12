package com.ram.buspass.features.profile.data

import com.ram.buspass.features.profile.domain.ProfileRepository
import com.ram.buspass.helper.resource.remote.api.ApiService

class ProfileRepoImpl(private val apiService: ApiService): ProfileRepository {
    override suspend fun getUserProfile(): ProfilePojo {
        try {
            return apiService.getProfile()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}