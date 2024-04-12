package com.ram.buspass.helper.resource.remote.api


import com.ram.buspass.features.chanagePassword.domain.ChangePasswordDto
import com.ram.buspass.features.editProfile.domain.EditModelDto
import com.ram.buspass.features.locationView.data.LocationViewPojo
import com.ram.buspass.features.login.data.LoginPojo
import com.ram.buspass.features.login.domain.LoginUserModelDto
import com.ram.buspass.features.register.data.ResponsePojo
import com.ram.buspass.features.register.domain.UserModelDto
import com.ram.buspass.features.ticketBook.domain.BookDto
import com.ram.buspass.features.updateBusLocation.domain.BusLocationDto
import com.ram.buspass.features.bookTicket.data.BookingPojo
import com.ram.buspass.features.chanagePassword.data.ChangePasswordPojo
import com.ram.buspass.features.editProfile.data.EditProfilePojo
import com.ram.buspass.features.passVerify.data.PassVerifyPojo
import com.ram.buspass.features.profile.data.ProfilePojo
import com.ram.buspass.features.ticketBook.data.TicketBookPojo
import com.ram.buspass.features.ticketBook.data.TicketBookingPojo
import com.ram.buspass.features.updateBusLocation.data.LocationPojo
import com.ram.buspass.features.verifyTicket.data.VerifyTicketPojo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {


    @POST("api/register/user/")
    suspend fun registerUser(
        @Body userModelDto: UserModelDto
    ): ResponsePojo?

    @POST("api/login/user/")
    suspend fun loginUser(
        @Body loginUserModelDto: LoginUserModelDto
    ): LoginPojo?

    @GET("/api/user/profile/")
    suspend fun getProfile(): ProfilePojo
    @PUT("/api/user/profile/edit/")
    suspend fun editUserProfile(@Body editModelDto: EditModelDto): EditProfilePojo?

    @PATCH("/api/change/password/")
    suspend fun getChangePassword(@Body changePasswordDto: ChangePasswordDto): ChangePasswordPojo?

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

    @POST("api/update/bus/location/")
    suspend fun updateBusLocation(@Body busLocationDto: BusLocationDto): LocationPojo?

    @GET("api/view_bus_location/")
    suspend fun getLocationView(): LocationViewPojo
    @GET("api/pass/verify/")
    suspend fun getPassVerify(): PassVerifyPojo?

}