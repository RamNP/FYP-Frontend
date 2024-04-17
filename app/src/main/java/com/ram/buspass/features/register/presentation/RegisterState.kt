package com.ram.buspass.features.register.presentation

import com.ram.buspass.features.register.data.RegisterPojo

data class RegisterState(
    val isLoading: Boolean  = false,
    val isData: RegisterPojo? = null,
    val isError: String = ""
)