package com.example.assignmentwbpo.data

import java.net.URL

data class UserJsonReponse (
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val data: List<UserData>

)
data class UserData(
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: URL
)

data class RegData(
    val id: Int,
    val token: String
)