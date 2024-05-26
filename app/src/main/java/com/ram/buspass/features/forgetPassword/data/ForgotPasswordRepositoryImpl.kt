package com.ram.buspass.features.forgetPassword.data

import com.ram.buspass.features.forgetPassword.domain.ForgotPasswordDAO
import com.ram.buspass.features.forgetPassword.domain.ForgotPasswordPoJo
import com.ram.buspass.features.forgetPassword.domain.ForgotPasswordRepository
import com.ram.buspass.helper.resource.remote.api.ApiService

class ForgotPasswordRepositoryImpl(private val apiService: ApiService) : ForgotPasswordRepository {
    override suspend fun getUpdatePassword(
        email: String?,
        newPassword: String?
    ): ForgotPasswordPoJo? {
        return apiService.getUpdatePassword(ForgotPasswordDAO(email = email, newPassword = newPassword))
    }
}