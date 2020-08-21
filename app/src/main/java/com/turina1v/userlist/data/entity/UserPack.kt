package com.turina1v.userlist.data.entity

import com.google.gson.annotations.SerializedName

data class UserPack(
    @SerializedName("data")
    val users: List<User>
)
