package com.ram.buspass.di


import android.content.Context
import androidx.room.Room
import com.ram.buspass.features.bookingDetails.data.BookingDetailsRepositoryImpl
import com.ram.buspass.features.bookingDetails.domain.BookingDetailsRepository
import com.ram.buspass.features.login.data.LoginRepoImpl
import com.ram.buspass.features.login.domain.LoginRepository
import com.ram.buspass.features.register.data.RegisterRepoImpl
import com.ram.buspass.features.register.domain.RegisterRepository
import com.ram.buspass.features.ticketBook.data.TicketBookRepositoryImpl
import com.ram.buspass.features.ticketBook.domain.TicketBookRepository
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
    fun provideTicketBookRepositoryImpl(apiService: ApiService): TicketBookRepository {
        return TicketBookRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideUserBookingDetailsRepositoryImpl(apiService: ApiService): BookingDetailsRepository {
        return BookingDetailsRepositoryImpl(apiService)
    }



}
