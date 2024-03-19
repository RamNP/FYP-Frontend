package com.ram.buspass.features.login.domain

import com.ram.buspass.features.helper.resource.remote.api.model.login.LoginPojo

interface LoginRepository {

    suspend fun getLoginUser(email: String?, password: String?): LoginPojo?
}