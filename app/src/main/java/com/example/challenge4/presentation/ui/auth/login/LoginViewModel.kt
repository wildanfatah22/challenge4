package com.example.challenge4.presentation.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.challenge4.domain.model.User
import com.example.challenge4.domain.usecase.UserUseCase

class LoginViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    fun getUser(email: String, password: String) : LiveData<User?> {
        return userUseCase.getUserByEmailAndPassword(email, password)
    }
}