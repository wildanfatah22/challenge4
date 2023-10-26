package com.example.challenge4.presentation.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.challenge4.domain.model.User
import com.example.challenge4.domain.usecase.UserUseCase

class RegisterViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun registerUser(user: User) = userUseCase.insertUser(user)

    fun getUserEmail(email: String): LiveData<User?> = userUseCase.getUserEmail(email)

}