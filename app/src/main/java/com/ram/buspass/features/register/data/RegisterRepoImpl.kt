package com.ram.buspass.features.register.data

import com.ram.buspass.helper.resource.remote.api.ApiService
import com.ram.buspass.helper.resource.remote.api.model.register.ResponsePojo
import com.ram.buspass.features.register.domain.RegisterRepository
import com.ram.buspass.features.register.domain.UserModelDAO

class RegisterRepoImpl(private val apiService: ApiService): RegisterRepository {
    override suspend fun getRegisterUser(email: String?, username:String?, password: String?, role: String?): ResponsePojo? {
        val user = UserModelDAO(email, username, password, role)
        return apiService.registerUser(user)
    }
}