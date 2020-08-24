package com.turina1v.userlist.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turina1v.userlist.App
import com.turina1v.userlist.domain.model.UserModel
import com.turina1v.userlist.domain.repository.UserRepository
import com.turina1v.userlist.domain.usecase.LoadUserListUseCase
import com.turina1v.userlist.domain.usecase.LoadUsersResult
import com.turina1v.userlist.domain.usecase.ReloadUserListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserListViewModel : ViewModel() {
    @Inject
    lateinit var repository: UserRepository

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
        App.component.inject(this)
        viewModelScope.launch {
            _loaderLiveData.value = true
            when (val result = LoadUserListUseCase(repository).execute()) {
                is LoadUsersResult.Success -> {
                    _usersLiveData.value = result.users
                }
                is LoadUsersResult.Error -> _errorLiveData.value =
                    result.errorMessage
            }
            _loaderLiveData.value = false
        }
    }

    fun reloadUsers(){
        viewModelScope.launch {
            _loaderLiveData.value = true
            when (val result = ReloadUserListUseCase(repository).execute()) {
                is LoadUsersResult.Success -> {
                    _usersLiveData.value = result.users
                }
                is LoadUsersResult.Error -> _errorLiveData.value =
                    result.errorMessage
            }
            _loaderLiveData.value = false
        }
    }
}
