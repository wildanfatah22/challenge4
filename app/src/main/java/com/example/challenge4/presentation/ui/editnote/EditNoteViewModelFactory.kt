package com.example.challenge4.presentation.ui.editnote

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.domain.usecase.NoteUseCase
import com.example.challenge4.presentation.di.Injection

class EditNoteViewModelFactory(private val noteUseCase: NoteUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditNoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditNoteViewModel(noteUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        @Volatile
        private var instance: EditNoteViewModelFactory? = null

        fun getInstance(context: Context): EditNoteViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: EditNoteViewModelFactory(
                    Injection.provideNoteUseCase(context)
                )
            }
    }
}