package com.ram.buspass.features.chanagePassword.domain

import com.ram.buspass.helper.resource.remote.api.model.changePassword.ChangePasswordPojo

interface ChangePasswordRepository {

    suspend fun getPassword(email: String? = null,oldPassword: String? = null, newPassword: String? = null): ChangePasswordPojo?
}