package com.ram.buspass.features.profile.domain

import com.ram.buspass.features.profile.data.ProfilePojo

interface  ProfileRepository {
    suspend fun getUserProfile(): ProfilePojo


}