package com.ram.buspass.features.register.presentation

import com.ram.buspass.features.register.data.RegisterPojo

data class RegisterState(
    val isLoading: Boolean  = false,
    val data: RegisterPojo? = null,
    val isError: String = "",
    val message: String? = null
)