package com.turina1v.userlist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.turina1v.userlist.domain.model.UserModel

@Database(entities = [UserModel::class], version = 2, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
