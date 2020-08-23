package com.turina1v.userlist.di

import com.turina1v.userlist.ui.userlist.UserListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(viewModel: UserListViewModel)
}
