package com.ram.buspass.features.forgetPassword.domain

interface ForgotPasswordRepository {
    suspend fun getUpdatePassword(email: String?, newPassword: String?):ForgotPasswordPoJo?

}