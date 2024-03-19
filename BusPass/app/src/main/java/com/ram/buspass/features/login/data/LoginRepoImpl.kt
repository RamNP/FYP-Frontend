package com.ram.buspass.features.login.data

import com.ram.buspass.features.helper.resource.remote.api.ApiService
import com.ram.buspass.features.helper.resource.remote.api.model.login.LoginPojo
import com.ram.buspass.features.login.domain.LoginRepository
import com.ram.buspass.features.login.domain.LoginUserModelDAO


class LoginRepoImpl (private val apiService: ApiService): LoginRepository{
    override suspend fun getLoginUser(email: String?, password: String?): LoginPojo? {
        val loginUser=  LoginUserModelDAO(email, password)
        return  apiService.loginUser(loginUser)
    }

}