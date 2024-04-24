package com.ram.buspass.features.passVerify.data

import com.ram.buspass.features.passVerify.domain.PassVerifyRepository
import com.ram.buspass.helper.resource.remote.api.ApiService

class PassVerifyRepoImpl(private val apiService: ApiService) : PassVerifyRepository {
    override suspend fun getVerifyPass(): PassVerifyPojo? {
        try {
            return apiService.getPassVerify()
        } catch (e: Exception) {
            throw Exception(e)

        }
    }

    override suspend fun getPassStatusApproved(passId: Int?): PassVerifyPojo? {
        try {
            return apiService.getPassStatus(passId)
        } catch (e: Exception) {
            throw Exception(e)

        }
    }


}