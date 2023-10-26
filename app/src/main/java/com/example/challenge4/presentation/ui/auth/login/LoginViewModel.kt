package com.example.challenge4.presentation.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge4.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _auth = MutableLiveData<String>()
    val auth : LiveData<String> = _auth

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun userAuthentication(email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.Main) {
            if (loginRepository.validateInput(email, password)) {
                try {
                    withContext(Dispatchers.Main) {
                        _auth.value = loginRepository.authenticate(email, password)
                    }
                } catch (e: Exception) {
                    _error.value = e.message
                } finally {
                    withContext(Dispatchers.Main) {
                        _isLoading.value = false
                    }
                }
            }
        }
    }

    fun saveToken(token: String) {
        viewModelScope.launch(Dispatchers.Main) {
            loginRepository.saveToken(token)
        }
    }

    fun checkLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            val token = loginRepository.loadToken()
            if (!token.isNullOrBlank() && token.isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    _auth.value = loginRepository.loadToken()
                }
            } else {
                withContext(Dispatchers.Main) {
                    _auth.value = ""
                }
            }
        }
    }
}