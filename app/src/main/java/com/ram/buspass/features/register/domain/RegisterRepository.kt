package com.ram.buspass.features.register.domain

import com.ram.buspass.features.register.data.RegisterPojo

interface RegisterRepository {

    suspend fun getRegisterUser(email: String?,username: String?, password: String?, role: String?): RegisterPojo?
}