package com.ram.buspass.features.editProfile.presentation

import com.ram.buspass.features.editProfile.data.EditProfilePojo

data class EditProfileState(
    val isLoading: Boolean  = false,
    val isData: EditProfilePojo? = null,
    val isError: String = ""
)