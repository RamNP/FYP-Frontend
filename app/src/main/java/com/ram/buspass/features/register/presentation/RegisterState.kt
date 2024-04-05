package com.ram.buspass.features.register.presentation

import com.ram.buspass.helper.resource.remote.api.model.register.ResponsePojo

data class RegisterState(
    val isLoading: Boolean  = false,
    val isData: ResponsePojo? = null,
    val isError: String = ""
)