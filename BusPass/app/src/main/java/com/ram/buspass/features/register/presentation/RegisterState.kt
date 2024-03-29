package com.ram.buspass.features.register.presentation

import com.ram.buspass.features.helper.resource.remote.api.model.ResponsePojo

data class RegisterState(
    val isLoading: Boolean  = false,
    val isData: ResponsePojo? = null,
    val isError: String = ""
)