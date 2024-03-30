package com.ram.buspass.features.helper.resource.remote.api


import com.ram.buspass.features.helper.resource.remote.api.model.booTicketDetails.BookPojo
import com.ram.buspass.features.helper.resource.remote.api.model.login.LoginPojo
import com.ram.buspass.features.helper.resource.remote.api.model.register.ResponsePojo
import com.ram.buspass.features.helper.resource.remote.api.model.ticketBook.TicketBookPojo
import com.ram.buspass.features.login.domain.LoginUserModelDAO
import com.ram.buspass.features.register.domain.UserModelDAO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {


    @POST("api/register/user/")
    suspend fun registerUser(
        @Body userModelDAO: UserModelDAO
    ): ResponsePojo?

    @POST("api/loginuser/")
    suspend fun loginUser(
        @Body loginUserModelDAO: LoginUserModelDAO
    ): LoginPojo?

    @GET("api/ticket/bus_details/")
    suspend fun getBookTicket():TicketBookPojo


    @POST("/api/book/ticket/")
    suspend fun getBookDetails(
    ): BookPojo



}