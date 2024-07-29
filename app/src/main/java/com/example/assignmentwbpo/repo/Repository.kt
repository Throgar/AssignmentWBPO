package com.example.assignmentwbpo.repo

import android.content.Context
import com.example.assignmentwbpo.api.RetrofitProvider
import com.example.assignmentwbpo.data.UserJsonResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.StateFlow
import okio.IOException
import retrofit2.http.GET
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


    @GET
    suspend fun getUsers(): Result<UserJsonResponse> {
        val client = RetrofitProvider().provideAPI(
            RetrofitProvider().providesRetrofitUsers(
                RetrofitProvider().provideClient()
            ))
        val resultUsers = client.getUsers().execute()
        if (resultUsers.isSuccessful) {
            return Result.success(resultUsers.body()!!)
        } else {
            return Result.failure(IOException())
        }
    }

    fun hasToken(): Boolean {
        return sharedPrefs.getString("token", null) != null
    }

}