package com.ram.buspass.features.register.domain

import com.ram.buspass.features.register.data.ResponsePojo

interface RegisterRepository {

    suspend fun getRegisterUser(email: String? = null,username: String? = null, password: String? = null, role: String? = null): ResponsePojo?
}