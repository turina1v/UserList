package com.turina1v.userlist.data.repository

import com.turina1v.userlist.data.network.UserApiProvider
import com.turina1v.userlist.domain.model.UserModel
import com.turina1v.userlist.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    override suspend fun loadUsers(): List<UserModel> {
        val users = UserApiProvider.api.getUsers().users
        val models = mutableListOf<UserModel>()
        users.forEach {
            models.add(UserModel(it.avatar, it.firstName + " " + it.lastName, it.email))
        }
        return models
    }
}
