package com.turina1v.userlist.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserApiProvider {
    val api: UserApi = Retrofit.Builder()
        .baseUrl("https://reqres.in")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserApi::class.java)
}
