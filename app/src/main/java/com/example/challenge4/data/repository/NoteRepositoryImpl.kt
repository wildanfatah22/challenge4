package com.example.challenge4.data.repository

import androidx.lifecycle.LiveData
import com.example.challenge4.data.datasource.local.room.dao.NoteDao
import com.example.challenge4.data.datasource.local.room.entity.NoteEntity
import com.example.challenge4.domain.model.Note
import com.example.challenge4.domain.repository.NoteRepository
import com.example.challenge4.presentation.utils.AppExecutors

class NoteRepositoryImpl private constructor(
    private val noteDao: NoteDao,
    private val appExecutors: AppExecutors
) : NoteRepository {

    override fun getAllNotes(): LiveData<List<Note>> {
        TODO("Not yet implemented")
    }

    override fun insertNote(note: Note) {
        TODO("Not yet implemented")
    }

    override fun updateNote(note: Note) {
        TODO("Not yet implemented")
    }

    override fun deleteNote(note: Note) {
        TODO("Not yet implemented")
    }

    override fun getSelectedNote(id: Int): Note {
        TODO("Not yet implemented")
    }


    companion object {
        @Volatile
        private var instance: NoteRepositoryImpl? = null

        fun getInstance(
            noteDao: NoteDao,
            appExecutors: AppExecutors
        ): NoteRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: NoteRepositoryImpl(noteDao, appExecutors)
            }
    }

}