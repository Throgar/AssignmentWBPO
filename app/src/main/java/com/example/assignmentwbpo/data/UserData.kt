package com.example.assignmentwbpo.data

import java.net.URL

data class UserJsonReponse (
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<UserData>

)
data class UserData(
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: URL
)