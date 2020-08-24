package com.turina1v.userlist.data.repository

import com.turina1v.userlist.data.database.UserDatabase
import com.turina1v.userlist.data.network.UserApi
import com.turina1v.userlist.domain.model.UserModel
import com.turina1v.userlist.domain.repository.UserRepository

class UserRepositoryImpl(private val database: UserDatabase, private val api: UserApi) :
    UserRepository {
    override suspend fun loadUsers(): List<UserModel> {
        val dbUsers = database.userDao().getUsers()
        return if (dbUsers.isNullOrEmpty()) {
            val models = mutableListOf<UserModel>()
            val apiUsers = api.getUsers().users
            apiUsers.forEach {
                models.add(
                    UserModel(
                        avatar = it.avatar,
                        firstName = it.firstName,
                        lastName = it.lastName,
                        email = it.email
                    )
                )
            }
            database.userDao().insertUsers(models)
            models
        } else {
            dbUsers
        }
    }
}
