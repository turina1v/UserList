package com.turina1v.userlist.domain.usecase

import com.turina1v.userlist.data.database.UserDatabase
import com.turina1v.userlist.domain.model.UserModel

class SaveUserUseCase(private val database: UserDatabase, private val userModel: UserModel) {
    suspend fun execute(){
        database.userDao().updateUser(userModel)
    }
}
