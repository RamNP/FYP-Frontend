package com.ram.buspass.features.editProfile.presentation

import com.ram.buspass.features.editProfile.data.EditImagePojo

data class EditProfileImageState(
    val isLoading: Boolean  = false,
    val isData: EditImagePojo? = null,
    val isError: String = ""
)