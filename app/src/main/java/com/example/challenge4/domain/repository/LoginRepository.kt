package com.example.challenge4.domain.repository

import androidx.lifecycle.LiveData
import com.example.challenge4.data.local.room.entity.User

interface LoginRepository{

    suspend fun insertUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun getUserById(userId: Int): LiveData<User>

    suspend fun getUserByEmailAndPassword(email: String, password: String): LiveData<User?>

    suspend fun getUserByEmail(email: String): LiveData<User?>
}