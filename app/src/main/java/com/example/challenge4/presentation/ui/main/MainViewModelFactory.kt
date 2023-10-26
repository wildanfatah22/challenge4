package com.example.challenge4.presentation.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.data.repository.NoteRepositoryImpl
import com.example.challenge4.domain.usecase.NoteUseCase
import com.example.challenge4.domain.usecase.UserUseCase
import com.example.challenge4.presentation.di.Injection
import com.example.challenge4.presentation.ui.auth.login.LoginViewModelFactory

class MainViewModelFactory(private val noteUseCase: NoteUseCase) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(noteUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        @Volatile
        private var instance: MainViewModelFactory? = null

        fun getInstance(context: Context): MainViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: MainViewModelFactory(
                    Injection.provideNoteUseCase(context)
                )
            }
    }
}