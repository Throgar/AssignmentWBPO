package com.example.assignmentwbpo.api

import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {
    @Headers("Content-type: application-json" )
    @POST
    suspend fun registerUser(
        @Url url: String,
        @Field("email") email: String,
        @Field("password") password: String
    )

    @GET
    suspend fun getUsers(
        @Url url: String
    ): Result<String>

}