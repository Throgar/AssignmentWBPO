package com.example.assignmentwbpo.repo

import android.content.Context
import com.example.assignmentwbpo.api.ApiService
import com.example.assignmentwbpo.api.RetrofitProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import okio.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(@ApplicationContext context: Context) {

    val sharedPrefs = context.getSharedPreferences("registrationPrefs", Context.MODE_PRIVATE)
    suspend fun registerUser(email: String, password: String): Result<Unit> {
        val client = RetrofitProvider().provideAPI(
            RetrofitProvider().providesRetrofit(
                RetrofitProvider().provideClient()
            ))
        val resultRegister = client.registerUser(email, password).execute()
        if (resultRegister.isSuccessful) {
            sharedPrefs.edit().putString("token", resultRegister.message()).apply()
            return Result.success(Unit)
        } else {
            return Result.failure(IOException("Registration failure"))
        }

    }

    suspend fun getUsers(url: String) {

    }

    fun hasToken(): Boolean {
        return sharedPrefs.getString("token", null) != null
    }

}