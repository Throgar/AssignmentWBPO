package com.example.assignmentwbpo.repo

import com.example.assignmentwbpo.api.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {

    val base_url = "https://regres.in/api"
    val registerUrl = "/register"
    val loginUrl = "/login"
    val usersUrl = "/users"




}