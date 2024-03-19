package com.ram.buspass.features.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import com.google.gson.Gson
import com.ram.buspass.features.helper.resource.remote.api.model.login.AuthResponse
import okhttp3.Interceptor
import okhttp3.Response

class ClientInterceptor(private val context: Context): Interceptor{
    private val getSharedPreferences = context.getSharedPreferences("my_preferences", ComponentActivity.MODE_PRIVATE)

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = getAuthenticate()?.accessToken
        val request = chain.request().newBuilder()
            .addHeader("Authorization","Bearer $accessToken")
            .addHeader("Content-Type" ,"application/json").build()
        return chain.proceed(request)
    }

    private fun getPreferenceInstance(): SharedPreferences {
        return getSharedPreferences
    }

    fun getPreInstEditor(): SharedPreferences.Editor {
        return getPreferenceInstance().edit()
    }

    private fun getAuthenticate(): AuthResponse? {
        val jsonString = getSharedPreferences.getString("authentication", "")
        if (!jsonString.isNullOrEmpty()) {
            return try {
                Gson().fromJson(jsonString, AuthResponse::class.java)
            } catch (e: Exception) {
                null
            }
        }
        return null
    }

    fun installApp(): String {
        return getSharedPreferences.getString("appInstallation", "") ?: ""
    }

    fun getUserRole(): String {
        return getAuthenticate()?.role ?: ""
    }
}
