package com.turina1v.userlist.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.turina1v.userlist.data.database.UserDatabase
import com.turina1v.userlist.data.network.UserApi
import com.turina1v.userlist.data.repository.UserRepositoryImpl
import com.turina1v.userlist.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {
    @Singleton
    @Provides
    fun provideDatabase(context: Context?): UserDatabase {
        return Room.databaseBuilder(
            context!!,
            UserDatabase::class.java, "photo_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideContext(): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideRepository(database: UserDatabase, api: UserApi): UserRepository {
        return UserRepositoryImpl(database, api)
    }

    @Singleton
    @Provides
    fun provideApi(): UserApi = Retrofit.Builder()
        .baseUrl("https://reqres.in")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserApi::class.java)
}
