package com.example.challenge4.presentation.ui.auth.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.domain.usecase.UserUseCase
import com.example.challenge4.presentation.di.Injection

class RegisterViewModelFactory(private val userUseCase: UserUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(userUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        @Volatile
        private var instance: RegisterViewModelFactory? = null

        fun getInstance(context: Context): RegisterViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: RegisterViewModelFactory(
                    Injection.provideUserUseCase(context)
                )
            }
    }
}