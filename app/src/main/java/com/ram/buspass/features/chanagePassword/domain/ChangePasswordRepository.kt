package com.ram.buspass.features.chanagePassword.domain

import com.ram.buspass.features.chanagePassword.data.ChangePasswordPojo

interface ChangePasswordRepository {

    suspend fun getPassword(email: String? = null,oldPassword: String? = null, newPassword: String? = null): ChangePasswordPojo?
}