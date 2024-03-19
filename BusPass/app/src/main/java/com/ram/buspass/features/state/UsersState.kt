package com.ram.buspass.features.state

import com.ram.buspass.features.helper.resource.remote.api.model.login.LoginPojo


data class UsersState(
    val isLoading: Boolean = false,
    val isData: LoginPojo? = null,
    val isError: String = "",
)