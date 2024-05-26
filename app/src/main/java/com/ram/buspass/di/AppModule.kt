package com.ram.buspass.di


import android.content.Context
import com.ram.buspass.features.bookingTicket.data.BookingDetailsRepositoryImpl
import com.ram.buspass.features.bookingTicket.domain.BookingDetailsRepository
import com.ram.buspass.features.chanagePassword.data.ChangePasswordRepoImpl
import com.ram.buspass.features.chanagePassword.domain.ChangePasswordRepository
import com.ram.buspass.features.editProfile.data.EditProfileRepoImpl
import com.ram.buspass.features.editProfile.domain.EditProfileRepository
import com.ram.buspass.features.forgetPassword.data.ForgotPasswordRepositoryImpl
import com.ram.buspass.features.forgetPassword.domain.ForgotPasswordRepository
import com.ram.buspass.features.locationView.data.LocationViewRepoImpl
import com.ram.buspass.features.locationView.domain.LocationViewRepository
import com.ram.buspass.features.login.data.LoginRepoImpl
import com.ram.buspass.features.login.domain.LoginRepository
import com.ram.buspass.features.passVerify.data.PassVerifyRepoImpl
import com.ram.buspass.features.passVerify.domain.PassVerifyRepository
import com.ram.buspass.features.profile.data.ProfileRepoImpl
import com.ram.buspass.features.profile.domain.ProfileRepository
import com.ram.buspass.features.register.data.RegisterRepoImpl
import com.ram.buspass.features.register.domain.RegisterRepository
import com.ram.buspass.features.ticketBook.data.TicketBookRepositoryImpl
import com.ram.buspass.features.ticketBook.domain.TicketBookRepository
import com.ram.buspass.features.updateBusLocation.data.UpdateBusLocationRepoImpl
import com.ram.buspass.features.updateBusLocation.domain.UpdateBusLocationRepository
import com.ram.buspass.features.verifyTicket.data.VerifyTicketRepoImpl
import com.ram.buspass.features.verifyTicket.domain.VerifyTicketRepository
import com.ram.buspass.helper.resource.local.DatabaseHelper.Companion.getDatabaseInstance
import com.ram.buspass.helper.resource.local.UserDao
import com.ram.buspass.helper.resource.remote.api.ApiService
import com.ram.buspass.helper.resource.remote.api.RetrofitInstance.Companion.getRetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    // create Retrofit instance
    @Provides
    @Singleton
    fun provideDatabaseInstance(@ApplicationContext context: Context): UserDao {
        return getDatabaseInstance(context).userDao()
    }

     // retrofit instance
    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): ApiService {
        return getRetrofitInstance(context).create(ApiService::class.java)
    }



//FYP
    @Provides
    @Singleton
    fun provideRegisterRepoImpl(apiService: ApiService): RegisterRepository {
        return RegisterRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideLoginRepoImpl(apiService: ApiService): LoginRepository {
        return LoginRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideProfileRepoImpl(apiService: ApiService): ProfileRepository {
        return ProfileRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideEditProfileRepoImpl(apiService: ApiService): EditProfileRepository {
        return EditProfileRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun providePasswordChangeRepoImpl(apiService: ApiService): ChangePasswordRepository {
        return ChangePasswordRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideForgotPasswordRepoImpl(apiService: ApiService): ForgotPasswordRepository {
        return ForgotPasswordRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideTicketBookRepositoryImpl(apiService: ApiService): TicketBookRepository {
        return TicketBookRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideUserBookingDetailsRepositoryImpl(apiService: ApiService): BookingDetailsRepository {
        return BookingDetailsRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideBusLocationRepositoryImpl(apiService: ApiService): UpdateBusLocationRepository {
        return UpdateBusLocationRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideVerifyTicketRepositoryImpl(apiService: ApiService): VerifyTicketRepository {
        return VerifyTicketRepoImpl(apiService)
    }


    @Provides
    @Singleton
    fun provideVerifyPassRepositoryImpl(apiService: ApiService): PassVerifyRepository {
        return PassVerifyRepoImpl(apiService)
    }


    @Provides
    @Singleton
    fun provideLocationViewRepositoryImpl(apiService: ApiService): LocationViewRepository {
        return LocationViewRepoImpl(apiService)
    }



}
