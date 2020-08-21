package com.turina1v.userlist.data.network

import com.turina1v.userlist.data.entity.UserPack
import retrofit2.http.GET

interface UserApi {
    @GET("/api/users?page=2")
    suspend fun getUsers(): UserPack
}
