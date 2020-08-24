package com.turina1v.userlist.domain.usecase

import com.turina1v.userlist.domain.model.UserModel
import com.turina1v.userlist.domain.repository.UserRepository

class ReloadUserListUseCase(private val userRepository: UserRepository) {
    suspend fun execute(): LoadUsersResult {
        return try {
            userRepository.deleteAllUsers()
            LoadUsersResult.Success(
                userRepository.loadUsers()
            )
        } catch (e: Throwable) {
            LoadUsersResult.Error(
                e.message ?: "Error loading data"
            )
        }
    }
}

