package com.example.challenge4.presentation.ui.editnote

import androidx.lifecycle.ViewModel
import com.example.challenge4.domain.model.Note
import com.example.challenge4.domain.usecase.NoteUseCase

class EditNoteViewModel(private val noteUseCase: NoteUseCase) : ViewModel() {
    fun editNote(note: Note) {
        noteUseCase.updateNote(note)
    }

    fun deleteNote(note: Note) {
        noteUseCase.deleteNote(note)
    }
}