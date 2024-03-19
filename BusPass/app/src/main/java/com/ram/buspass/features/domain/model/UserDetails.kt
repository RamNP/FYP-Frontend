package com.ram.buspass.features.domain.model

data class UserDetails(
    val userNameOrEmailAddress: String,
    val password: String,
    val rememberClient: Boolean,
    val couponCode: String
)

