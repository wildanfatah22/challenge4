package com.example.challenge4.domain.repository

import androidx.lifecycle.LiveData
import com.example.challenge4.domain.model.Note

interface NoteRepository {
    fun getAllNotes(): LiveData<List<Note>>
    fun insertNote(note: Note)
    fun updateNote(note: Note)
    fun deleteNote(note: Note)

    fun getSelectedNote(id: Int): Note
}