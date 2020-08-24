package com.turina1v.userlist.domain.usecase

import com.turina1v.userlist.data.database.UserDatabase
import com.turina1v.userlist.domain.model.UserModel

class DeleteUserUseCase(private val database: UserDatabase, private val userModel: UserModel) {
    suspend fun execute(){
        database.userDao().deleteUser(userModel)
    }
}
