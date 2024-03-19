package com.ram.buspass.features.register.data

import com.ram.buspass.features.helper.resource.remote.api.ApiService
import com.ram.buspass.features.helper.resource.remote.api.model.ResponsePojo
import com.ram.buspass.features.register.domain.RegisterRepository
import com.ram.buspass.features.register.domain.UserModelDAO

class RegisterRepoImpl(private val apiService: ApiService): RegisterRepository {
    override suspend fun getRegisterUser(email: String?, password: String?, role: String?): ResponsePojo? {
        val user = UserModelDAO(email, password, role)
        return apiService.registerUser(user)
    }
}