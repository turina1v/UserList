package com.turina1v.userlist.data.database

import androidx.room.*
import com.turina1v.userlist.domain.model.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM table_users")
    suspend fun getUsers(): List<UserModel>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserModel>)

    @Update
    suspend fun updateUser(user: UserModel)

    @Delete
    suspend fun deleteUser(user: UserModel)
}
