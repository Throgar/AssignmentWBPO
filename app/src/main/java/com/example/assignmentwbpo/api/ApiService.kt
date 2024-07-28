package com.example.assignmentwbpo.api

import com.example.assignmentwbpo.data.UserData
import com.example.assignmentwbpo.data.RegData
import com.example.assignmentwbpo.data.UserJsonResponse
import com.example.assignmentwbpo.utils.Constants
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("register/")
    @FormUrlEncoded
    suspend fun registerUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegData>

    @GET(Constants.usersUrl)
    suspend fun getUsers(

    ): Call<UserJsonResponse>

}