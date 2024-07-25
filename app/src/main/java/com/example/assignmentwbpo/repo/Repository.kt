package com.example.assignmentwbpo.repo

import android.content.Context
import com.example.assignmentwbpo.api.ApiService
import okio.IOException
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService, context: Context) {

    val sharedPrefs = context.getSharedPreferences("registrationPrefs", Context.MODE_PRIVATE)
    suspend fun registerUser(email: String, password: String): Result<Unit> {
        val resultRegister = apiService.registerUser(email, password).execute()
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