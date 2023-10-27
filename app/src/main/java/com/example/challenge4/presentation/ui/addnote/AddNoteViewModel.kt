package com.example.challenge4.presentation.ui.addnote

import androidx.lifecycle.ViewModel
import com.example.challenge4.domain.model.Note
import com.example.challenge4.domain.usecase.NoteUseCase

class AddNoteViewModel(private val noteUseCase: NoteUseCase) : ViewModel() {
    fun insertNote(note: Note) {
        noteUseCase.insertNote(note)
    }
}