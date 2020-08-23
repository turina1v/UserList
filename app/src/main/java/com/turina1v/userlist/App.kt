package com.turina1v.userlist

import androidx.multidex.MultiDexApplication
import com.turina1v.userlist.di.AppComponent
import com.turina1v.userlist.di.AppModule
import com.turina1v.userlist.di.DaggerAppComponent

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        component = generateAppComponent()
    }

    private fun generateAppComponent(): AppComponent {
        return DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var component: AppComponent
    }
}
