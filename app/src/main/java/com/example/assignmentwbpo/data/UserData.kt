package com.example.assignmentwbpo.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.net.URL

data class UserJsonResponse (
    @SerializedName("page")
    @Expose
    val page: Int? = 0,
    @SerializedName("per_page")
    @Expose
    val perPage: Int? = 0,
    @SerializedName("total")
    @Expose
    val total: Int? = 0,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int? = 0,
    @SerializedName("data")
    @Expose
    var data: List<UserData>? = null

)
data class UserData(
    @SerializedName("email")
    @Expose
    val email: String? = null,
    @SerializedName("firs_name")
    @Expose
    val firstName: String? = null,
    @SerializedName("last_name")
    @Expose
    val lastName: String? = null,
    @SerializedName("url")
    @Expose
    val avatar: URL? = null
)



data class RegData(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("token")
    @Expose
    val token: String
)