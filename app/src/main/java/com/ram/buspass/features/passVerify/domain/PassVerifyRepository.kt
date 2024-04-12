package com.ram.buspass.features.passVerify.domain

import com.ram.buspass.features.passVerify.data.PassVerifyPojo

interface PassVerifyRepository {

    suspend fun getVerifyPass(): PassVerifyPojo?
}