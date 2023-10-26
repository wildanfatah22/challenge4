package com.example.challenge4.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.data.local.repository.LocalRepository

class MainViewModelFactory(private val localRepository: LocalRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(localRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}