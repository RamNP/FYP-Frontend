package com.ram.buspass.features.login.presentation

import com.ram.buspass.features.helper.resource.remote.api.model.login.LoginPojo

data class LoginState(
    val isLoading: Boolean  = false,
    val isData: LoginPojo? = null,
    val isError: String = ""
)