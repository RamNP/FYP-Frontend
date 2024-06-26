package com.ram.buspass.features.login.data

import com.ram.buspass.helper.resource.remote.api.ApiService
import com.ram.buspass.features.login.domain.LoginRepository
import com.ram.buspass.features.login.domain.LoginUserModelDto


class LoginRepoImpl (private val apiService: ApiService): LoginRepository{
    override suspend fun getLoginUser(email: String?, password: String?): LoginPojo? {
        val loginUser=  LoginUserModelDto(email, password)
        return  apiService.loginUser(loginUser)
    }

}