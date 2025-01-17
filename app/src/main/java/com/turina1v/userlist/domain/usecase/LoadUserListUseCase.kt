package com.turina1v.userlist.domain.usecase

import com.turina1v.userlist.domain.model.UserModel
import com.turina1v.userlist.domain.repository.UserRepository

class LoadUserListUseCase(private val userRepository: UserRepository) {

    suspend fun execute(): LoadUsersResult {
        return try {
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

sealed class LoadUsersResult {
    data class Success(val users: List<UserModel>) : LoadUsersResult()
    data class Error(val errorMessage: String) : LoadUsersResult()
}

