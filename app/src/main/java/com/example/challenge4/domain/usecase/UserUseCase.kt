package com.example.challenge4.domain.usecase

import androidx.lifecycle.LiveData
import com.example.challenge4.domain.model.User
import com.example.challenge4.domain.repository.AuthRepository

interface UserUseCase {

    fun insertUser(user: User)

    fun updateUser(user: User)

    fun deleteUser(user: User)

    fun getUserById(userId: Int): LiveData<User>

    fun getUserByEmailAndPassword(email: String, password: String): LiveData<User?>

}

class UserInteractor(private val authRepository: AuthRepository) : UserUseCase {
    override fun insertUser(user: User) = authRepository.insertUser(user)

    override fun updateUser(user: User) = authRepository.updateUser(user)

    override fun deleteUser(user: User) = authRepository.deleteUser(user)

    override fun getUserById(userId: Int) = authRepository.getUserById(userId)

    override fun getUserByEmailAndPassword(email: String, password: String) =
        authRepository.getUserByEmailAndPassword(email, password)

}