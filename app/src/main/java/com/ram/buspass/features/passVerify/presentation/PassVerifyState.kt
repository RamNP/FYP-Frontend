package com.ram.buspass.features.passVerify.presentation

import com.ram.buspass.helper.resource.remote.api.model.passVerify.PassVerifyPojo

data class PassVerifyState(
    val isLoading: Boolean  = false,
    val isData: PassVerifyPojo? = null,
    val isError: String? = null
)