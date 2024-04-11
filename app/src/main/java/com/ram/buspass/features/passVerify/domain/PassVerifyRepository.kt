package com.ram.buspass.features.passVerify.domain

import com.ram.buspass.helper.resource.remote.api.model.passVerify.PassVerifyPojo

interface PassVerifyRepository {

    suspend fun getVerifyPass(): PassVerifyPojo?
}