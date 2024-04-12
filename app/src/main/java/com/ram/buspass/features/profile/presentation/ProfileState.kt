package com.ram.buspass.features.profile.presentation

import com.ram.buspass.features.profile.data.ProfilePojo

data class ProfileState(
    val isLoading: Boolean  = false,
    val isData: ProfilePojo? = null,
    val isError: String? = null
)