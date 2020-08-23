package com.turina1v.userlist.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_users")
class UserModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val avatar: String,
    val firstName: String,
    val lastName: String,
    val email: String
) {
    fun getFullName(): String = "$firstName $lastName"
}
