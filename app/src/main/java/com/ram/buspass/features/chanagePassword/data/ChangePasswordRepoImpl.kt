package com.ram.buspass.features.chanagePassword.data

import com.ram.buspass.features.chanagePassword.domain.ChangePasswordDto
import com.ram.buspass.features.chanagePassword.domain.ChangePasswordRepository
import com.ram.buspass.helper.resource.remote.api.ApiService

class ChangePasswordRepoImpl (private val apiService: ApiService):ChangePasswordRepository{
    override suspend fun getPassword(email: String?, oldPassword: String?, newPassword: String?
    ): ChangePasswordPojo? {
        val passwordDetail = ChangePasswordDto(email, oldPassword, newPassword)
        return apiService.getChangePassword(passwordDetail)
    }

}

