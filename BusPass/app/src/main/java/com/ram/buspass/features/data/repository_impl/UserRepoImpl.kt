//package com.ram.buspass.features.data.repository_impl
//
//import com.ram.buspass.features.helper.resource.remote.api.ApiService
//import com.ram.buspass.features.helper.resource.remote.api.model.LoginPojo
//import com.ram.buspass.features.domain.model.UserDetails
//import com.ram.buspass.features.domain.repository.UserRepository
//
//class UserRepoImpl(private val apiService: ApiService) : UserRepository {
//    override suspend fun authenticateUser(email: String, password: String): LoginPojo {
//        try {
//
//            val userDetails = UserDetails(
//                userNameOrEmailAddress = email,
//                password = password,
//                rememberClient = false,
//                couponCode = ""
//            )
//            val response = apiService.getUser(userDetails)
//            return response ?: throw Exception("Login Error: ${response?.error}")
//        } catch (e: Exception) {
//            throw Exception(e)
//        }
//    }
//
////    override suspend fun insertUser(name: String?, password: String?) {
////        try {
////            val userList = listOf(User(name = name, password = password))
//////            userDao.insertUser(userList)
////        } catch (e: Exception) {
////            throw Exception("error $e")
////        }
////    }
//
//
//}