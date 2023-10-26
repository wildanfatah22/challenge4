package com.example.challenge4.presentation.di

import android.content.Context

object Injection {
    private fun provideUserRepository(context: Context): IUserRepository {
        val database = AppDatabase.getInstance(context)

        val localDataSource = UserDatabaseDataSource.getInstance(database.userDao())
        val appExecutors = AppExecutors()

        return UserRepository.getInstance(localDataSource, appExecutors)
    }

}