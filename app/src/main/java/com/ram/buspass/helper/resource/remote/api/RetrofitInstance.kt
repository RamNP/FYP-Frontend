package com.ram.buspass.helper.resource.remote.api

import android.content.Context
import com.ram.buspass.helper.ClientInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object {
        private var retrofitInstance: Retrofit? = null

        /**
         * Get the Retrofit instance.
         * @param context The context required for creating OkHttpClient.
         * @return Retrofit instance.
         */
        fun getRetrofitInstance(context: Context): Retrofit {
            if (retrofitInstance == null) {
                // Create the object of HttpLoggingInterceptor
                val httpLoggingInterceptor =
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                // Create object of OkHttpClient
                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(ClientInterceptor(context)) // Add the custom interceptor
                    .connectTimeout(30, TimeUnit.SECONDS) // Set connection timeout
                    .readTimeout(30, TimeUnit.SECONDS) // Set read timeout
                    .writeTimeout(30, TimeUnit.SECONDS) // Set write timeout
                    .build()
                // Return the Retrofit instance
                retrofitInstance = Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()) // json converter
                    .build()
            }
            return retrofitInstance!!
        }
    }
}