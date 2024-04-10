package com.ram.buspass.helper.resource.remote.api


import com.ram.buspass.features.login.domain.LoginUserModelDAO
import com.ram.buspass.features.register.domain.UserModelDAO
import com.ram.buspass.features.ticketBook.domain.BookDto
import com.ram.buspass.features.updateBusLocation.domain.BusLocationDataDAO
import com.ram.buspass.helper.resource.remote.api.model.booTicketDetails.BookingPojo
import com.ram.buspass.helper.resource.remote.api.model.login.LoginPojo
import com.ram.buspass.helper.resource.remote.api.model.register.ResponsePojo
import com.ram.buspass.helper.resource.remote.api.model.ticketBook.TicketBookPojo
import com.ram.buspass.helper.resource.remote.api.model.ticketBook.TicketBookingPojo
import com.ram.buspass.helper.resource.remote.api.model.updateBusLocation.LocationPojo
import com.ram.buspass.helper.resource.remote.api.model.verifyTicket.VerifyTicketPojo
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
    suspend fun getBookTicket(): TicketBookPojo

    @POST("/api/book/ticket/")
    suspend fun getBookDetails(
        @Body bookDto: BookDto
    ): TicketBookingPojo

    @GET("api/booking/ticket/")
    suspend fun getUserBooking(): BookingPojo

    @GET("/api/verify/ticket/")
    suspend fun getVerifyTicket(): VerifyTicketPojo

    @POST("update/bus/location/")
    suspend fun updateBusLocation(@Body busLocationDataDAO: BusLocationDataDAO): LocationPojo?
}