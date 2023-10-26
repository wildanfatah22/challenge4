package com.example.challenge4.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.data.repository.NoteRepositoryImpl

class MainViewModelFactory(private val noteRepositoryImpl: NoteRepositoryImpl) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(noteRepositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}