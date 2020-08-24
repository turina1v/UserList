package com.turina1v.userlist.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "table_users")
@Parcelize
class UserModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val avatar: String,
    var firstName: String,
    var lastName: String,
    var email: String
): Parcelable {
    fun getFullName(): String = "$firstName $lastName"
}
