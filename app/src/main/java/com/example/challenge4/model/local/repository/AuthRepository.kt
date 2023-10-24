package com.example.challenge4.model.local.repository

interface AuthRepository{

    companion object {
        const val KEY_TOKEN = "token"
        const val SHARED_PREF = "shared_pref"
    }

    suspend fun validateInput(email: String, password: String): Boolean
    suspend fun authenticate(email: String, password: String): String
    suspend fun saveToken(token: String)
    suspend fun loadToken(): String?
}