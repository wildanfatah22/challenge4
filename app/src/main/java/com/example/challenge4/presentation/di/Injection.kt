package com.example.challenge4.presentation.di

import android.content.Context
import com.example.challenge4.data.local.repository.AuthRepositoryImpl
import com.example.challenge4.data.local.room.database.NoteDb
import com.example.challenge4.presentation.utils.AppExecutors

object Injection {

    fun provideAuthRepository(context: Context) : AuthRepositoryImpl {
        val database = NoteDb.getDatabase(context)
        val dao = database.userDao()
        val appExecutors = AppExecutors()

        return AuthRepositoryImpl.getInstance(dao, appExecutors)
    }
}