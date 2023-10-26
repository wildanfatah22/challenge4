package com.example.challenge4.domain.usecase

import androidx.lifecycle.LiveData
import com.example.challenge4.domain.model.Note
import com.example.challenge4.domain.repository.NoteRepository

interface NoteUseCase {

    fun getAllNote(): LiveData<List<Note>>

    fun insertNote(user: Note)

    fun updateNote(user: Note)

    fun deleteNote(user: Note)

    fun getSelectedNote(id: Int): LiveData<Note>


}

class NoteInteractor(private val noteRepository: NoteRepository) : NoteUseCase {

    override fun getAllNote() = noteRepository.getAllNotes()

    override fun insertNote(user: Note) = noteRepository.insertNote(user)

    override fun updateNote(user: Note) = noteRepository.updateNote(user)

    override fun deleteNote(user: Note) = noteRepository.deleteNote(user)

    override fun getSelectedNote(id: Int) = noteRepository.getSelectedNote(id)

}