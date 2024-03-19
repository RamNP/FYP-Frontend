package com.ram.buspass.features.register.domain

import com.ram.buspass.features.helper.resource.remote.api.model.ResponsePojo

interface RegisterRepository {

    suspend fun getRegisterUser(email: String? = null, password: String? = null, role: String? = null): ResponsePojo?
}