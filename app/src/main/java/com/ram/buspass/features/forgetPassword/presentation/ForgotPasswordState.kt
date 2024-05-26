package com.ram.buspass.features.forgetPassword.presentation

import com.ram.buspass.features.forgetPassword.domain.ForgotPasswordPoJo

data class ForgotPasswordState(
    val isLoading: Boolean  = false,
    val isData: ForgotPasswordPoJo? = null,
    val isError: String = ""
)