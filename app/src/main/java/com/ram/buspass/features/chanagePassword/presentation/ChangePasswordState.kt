package com.ram.buspass.features.chanagePassword.presentation

import com.ram.buspass.features.chanagePassword.data.ChangePasswordPojo

data class ChangePasswordState(
    val isLoading: Boolean  = false,
    val isData: ChangePasswordPojo? = null,
    val isError: String = ""
)