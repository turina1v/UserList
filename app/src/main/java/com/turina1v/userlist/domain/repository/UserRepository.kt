package com.turina1v.userlist.domain.repository

import com.turina1v.userlist.domain.model.UserModel

interface UserRepository {
    suspend fun loadUsers(): List<UserModel>
    suspend fun deleteAllUsers()
}
