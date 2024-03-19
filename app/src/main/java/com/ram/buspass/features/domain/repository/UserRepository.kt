package com.ram.buspass.features.domain.repository

import com.ram.buspass.features.helper.resource.remote.api.model.login.LoginPojo

interface UserRepository {
    suspend fun authenticateUser(email: String, password: String): LoginPojo?
}
