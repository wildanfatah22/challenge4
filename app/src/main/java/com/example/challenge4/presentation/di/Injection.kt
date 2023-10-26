package com.example.challenge4.presentation.di

import android.content.Context
import com.example.challenge4.data.datasource.local.NoteDbDataSource
import com.example.challenge4.data.datasource.local.UserDbDataSource
import com.example.challenge4.data.repository.AuthRepositoryImpl
import com.example.challenge4.data.repository.NoteRepositoryImpl
import com.example.challenge4.data.datasource.local.room.database.NoteDb
import com.example.challenge4.domain.repository.AuthRepository
import com.example.challenge4.domain.repository.NoteRepository
import com.example.challenge4.domain.usecase.NoteInteract
import com.example.challenge4.domain.usecase.NoteUseCase
import com.example.challenge4.domain.usecase.UserInteract
import com.example.challenge4.domain.usecase.UserUseCase
import com.example.challenge4.presentation.utils.AppExecutors

object Injection {

    private fun provideAuthRepository(context: Context) : AuthRepository {
        val database = NoteDb.getDatabase(context)
        val dataSource = UserDbDataSource.getInstance(database.userDao())
        val appExecutors = AppExecutors()

        return AuthRepositoryImpl.getInstance(dataSource, appExecutors)
    }

    fun provideUserUseCase(context: Context): UserUseCase {
        val repository = provideAuthRepository(context)
        return UserInteract(repository)
    }

    private fun provideNoteRepository(context : Context) : NoteRepository {
        val database = NoteDb.getDatabase(context)
        val dataSource = NoteDbDataSource.getInstance(database.noteDao())
        val appExecutors = AppExecutors()

        return NoteRepositoryImpl.getInstance(dataSource, appExecutors)
    }
    fun providePlayerUseCase(context: Context): NoteUseCase {
        val repository = provideNoteRepository(context)
        return NoteInteract(repository)
    }

}