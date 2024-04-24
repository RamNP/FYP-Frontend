package com.ram.buspass.helper.resource.remote.api


import com.ram.buspass.features.bookingTicket.data.BookingPojo
import com.ram.buspass.features.chanagePassword.data.ChangePasswordPojo
import com.ram.buspass.features.chanagePassword.domain.ChangePasswordDto
import com.ram.buspass.features.editProfile.data.EditProfilePojo
import com.ram.buspass.features.editProfile.domain.EditModelDto
import com.ram.buspass.features.locationView.data.LocationViewPojo
import com.ram.buspass.features.login.data.LoginPojo
import com.ram.buspass.features.login.domain.LoginUserModelDto
import com.ram.buspass.features.passVerify.data.PassVerifyPojo
import com.ram.buspass.features.profile.data.ProfilePojo
import com.ram.buspass.features.register.data.RegisterPojo
import com.ram.buspass.features.register.domain.UserModelDto
import com.ram.buspass.features.ticketBook.data.TicketBookPojo
import com.ram.buspass.features.ticketBook.data.TicketBookingPojo
import com.ram.buspass.features.ticketBook.domain.BookDto
import com.ram.buspass.features.updateBusLocation.data.BookingBusPojo
import com.ram.buspass.features.updateBusLocation.data.LocationPojo
import com.ram.buspass.features.updateBusLocation.domain.BusLocationDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {

    @POST("api/register/user/") // api/register/user/
    suspend fun registerUser(
        @Body userModelDto: UserModelDto
    ): RegisterPojo?

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
    suspend fun getVerifyTicket(): PassVerifyPojo

    @POST("api/update/bus/location/")
    suspend fun updateBusLocation(@Body busLocationDto: BusLocationDto): LocationPojo?

    @GET("api/view_bus_location/")
    suspend fun getLocationView(): LocationViewPojo

    @GET("api/booking/bus/details/")
    suspend fun getBookingBus(): BookingBusPojo?
    @GET("api/pass/verify/")
    suspend fun getPassVerify(): PassVerifyPojo?
    @PATCH("/api/pass/activate/")
    suspend fun getPassStatus(@Query("pass_id") passId: Int?): PassVerifyPojo

}