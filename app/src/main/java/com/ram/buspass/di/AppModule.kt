package com.ram.buspass.di


import android.content.Context
import androidx.room.Room
import com.ram.buspass.features.bookingDetails.data.BookingDetailsRepositoryImpl
import com.ram.buspass.features.bookingDetails.domain.BookingDetailsRepository
import com.ram.buspass.features.chanagePassword.data.ChangePasswordRepoImpl
import com.ram.buspass.features.chanagePassword.domain.ChangePasswordRepository
import com.ram.buspass.features.editProfile.data.EditProfileRepoImpl
import com.ram.buspass.features.editProfile.domain.EditProfileRepository
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
import com.ram.buspass.helper.ClientInterceptor
import com.ram.buspass.helper.resource.local.AppDatabase
import com.ram.buspass.helper.resource.local.UserDao
import com.ram.buspass.helper.resource.remote.api.ApiConstants
import com.ram.buspass.helper.resource.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabaseInstance(@ApplicationContext context: Context): UserDao {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "User_DB"
        ).fallbackToDestructiveMigration().build().usersDao()
    }

    //Retrofit instance create
    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): ApiService {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ClientInterceptor(context))
            .addInterceptor(httpLoggingInterceptor).build()
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
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



}
