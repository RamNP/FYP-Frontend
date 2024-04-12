package com.ram.buspass.features.login.domain

import com.ram.buspass.features.login.data.LoginPojo

interface LoginRepository {

    suspend fun getLoginUser(email: String?, password: String?): LoginPojo?
}