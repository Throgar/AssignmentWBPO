package com.example.assignmentwbpo.api

import com.example.assignmentwbpo.data.UserData
import com.example.assignmentwbpo.utils.Constants
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @Headers("Content-type: application-json" )
    @POST(Constants.registerUrl)
    suspend fun registerUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<String>

    @GET(Constants.usersUrl)
    suspend fun getUsers(

    ): Call<List<UserData>>

}