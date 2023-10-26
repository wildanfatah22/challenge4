package com.example.challenge4.data.local.repository

import android.content.SharedPreferences
import com.example.challenge4.data.local.room.database.NoteDb
import com.example.challenge4.data.local.room.entity.dummyUser
import com.example.challenge4.domain.repository.LoginRepository
import kotlinx.coroutines.delay

class LocalRepository(
    private val sharedPreferences: SharedPreferences,
    private val noteDb: NoteDb,
) : LoginRepository {

    companion object {
        private const val KEY_TOKEN = "token"
        const val PREF_NAME = "sharedPref"
    }

    override suspend fun validateInput(email: String, password: String): Boolean {
        delay(3000)
        val emailPattern = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        val isEmailValid = emailPattern.matches(email)

        val isPasswordValid = password.length >= 8

        return isEmailValid && isPasswordValid
    }

    override suspend fun authenticate(email: String, password: String): String {
        val user = dummyUser.find { it.email == email && it.password == password }
        return user?.let { "token" }
            ?: throw UnsupportedOperationException("Email and Password Incorrect!")
    }

    override suspend fun saveToken(token: String) =
        sharedPreferences.edit().putString(KEY_TOKEN, null).apply()

    override suspend fun loadToken(): String? = sharedPreferences.getString(KEY_TOKEN, null)


}