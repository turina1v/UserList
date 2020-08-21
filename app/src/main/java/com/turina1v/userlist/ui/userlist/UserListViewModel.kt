package com.turina1v.userlist.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turina1v.userlist.data.repository.UserRepositoryImpl
import com.turina1v.userlist.domain.model.UserModel
import com.turina1v.userlist.domain.usecase.LoadUserListUseCase
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {
    private val _usersLiveData: MutableLiveData<List<UserModel>> = MutableLiveData()
    val usersLiveData: LiveData<List<UserModel>>
        get() = _usersLiveData

    private val _loaderLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loaderLiveData: LiveData<Boolean>
        get() = _loaderLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    init {
        viewModelScope.launch {
            _loaderLiveData.value = true
            when (val result = LoadUserListUseCase(UserRepositoryImpl()).execute()) {
                is LoadUserListUseCase.LoadUsersResult.Success -> {
                    _usersLiveData.value = result.users
                }
                is LoadUserListUseCase.LoadUsersResult.Error -> _errorLiveData.value =
                    result.errorMessage
            }
            _loaderLiveData.value = false
        }
    }
}