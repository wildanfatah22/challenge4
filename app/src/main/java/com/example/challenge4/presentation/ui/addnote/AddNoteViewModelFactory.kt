package com.example.challenge4.presentation.ui.addnote

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.domain.usecase.NoteUseCase
import com.example.challenge4.presentation.di.Injection


class AddNoteViewModelFactory(private val noteUseCase: NoteUseCase): ViewModelProvider.Factory {
    
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddNoteViewModel(noteUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        @Volatile
        private var instance: AddNoteViewModelFactory? = null

        fun getInstance(context: Context): AddNoteViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: AddNoteViewModelFactory(
                    Injection.provideNoteUseCase(context)
                )
            }
    }
}