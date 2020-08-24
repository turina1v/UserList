package com.turina1v.userlist.ui.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turina1v.userlist.App
import com.turina1v.userlist.data.database.UserDatabase
import com.turina1v.userlist.domain.model.UserModel
import com.turina1v.userlist.domain.usecase.DeleteUserUseCase
import com.turina1v.userlist.domain.usecase.SaveUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDetailsViewModel : ViewModel() {
    @Inject
    lateinit var database: UserDatabase

    var userModel: UserModel? = null

    private val _userModelLiveData: MutableLiveData<UserModel> = MutableLiveData()
    val userLiveData: LiveData<UserModel>
        get() = _userModelLiveData

    init {
        App.component.inject(this)
    }

    fun setUser(user: UserModel?) {
        user?.let {
            _userModelLiveData.value = it
            userModel = it
        }
    }

    fun saveUser(firstName: String, lastName: String, email: String) {
        userModel?.let {
            it.firstName = firstName
            it.lastName = lastName
            it.email = email
                viewModelScope.launch {
                    SaveUserUseCase(database, it).execute()
                }
        }
    }

    fun deleteUser() {
        userModel?.let {
            viewModelScope.launch {
                DeleteUserUseCase(database, it).execute()
            }
        }
    }
}
