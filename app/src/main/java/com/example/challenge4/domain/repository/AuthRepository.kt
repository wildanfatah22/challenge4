package com.example.challenge4.domain.repository

import androidx.lifecycle.LiveData
import com.example.challenge4.domain.model.User

interface AuthRepository{

    suspend fun insertUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun getUserById(userId: Int): LiveData<User>

    suspend fun getUserByEmailAndPassword(email: String, password: String): LiveData<User?>

}