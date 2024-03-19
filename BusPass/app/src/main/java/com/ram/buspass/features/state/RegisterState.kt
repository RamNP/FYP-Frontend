package com.edu.vplayer.features.presentation.state

data class RegisterState (
    val isLoading: Boolean = false,
    val isSuccess: Boolean? = false,
    val isError: String = "",
)