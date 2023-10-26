package com.example.challenge4.domain.repository

import androidx.lifecycle.LiveData
import com.example.challenge4.domain.model.User

interface AuthRepository {

    fun insertUser(user: User)

    fun updateUser(user: User)

    fun deleteUser(user: User)

    fun getUserById(userId: Int): LiveData<User>

    fun getUserByEmailAndPassword(email: String, password: String): LiveData<User?>

    fun getUserEmail(email: String): LiveData<User?>
}