package com.ram.buspass.features.state

import com.ram.buspass.features.login.data.LoginPojo


data class UsersState(
    val isLoading: Boolean = false,
    val isData: LoginPojo? = null,
    val isError: String = "",
)