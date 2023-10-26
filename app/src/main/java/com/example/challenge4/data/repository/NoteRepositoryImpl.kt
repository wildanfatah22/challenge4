package com.example.challenge4.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.challenge4.data.datasource.local.NoteDbDataSource
import com.example.challenge4.data.datasource.local.room.dao.NoteDao
import com.example.challenge4.data.datasource.local.room.entity.NoteEntity
import com.example.challenge4.data.mapper.DataMapper
import com.example.challenge4.domain.model.Note
import com.example.challenge4.domain.repository.NoteRepository
import com.example.challenge4.presentation.utils.AppExecutors

class NoteRepositoryImpl private constructor(
    private val noteDbDataSource: NoteDbDataSource,
    private val appExecutors: AppExecutors
) : NoteRepository {

    override fun getAllNotes(): LiveData<List<Note>> {
        return noteDbDataSource.getAllNote().map { DataMapper.noteMapEntitiesToDomain(it) }
    }

    override fun insertNote(note: Note) {
        appExecutors.diskIO.execute { noteDbDataSource.insertNote(DataMapper.noteDomainToEntity(note)) }
    }

    override fun updateNote(note: Note) {
        appExecutors.diskIO.execute { noteDbDataSource.updateNote(DataMapper.noteDomainToEntity(note)) }
    }

    override fun deleteNote(note: Note) {
        appExecutors.diskIO.execute { noteDbDataSource.deleteNote(DataMapper.noteDomainToEntity(note)) }
    }

    override fun getSelectedNote(id: Int): LiveData<Note> {
        return noteDbDataSource.getSelectedNote(id)
            .map { DataMapper.oneNoteEntityToDomain(it) }
    }


    companion object {
        @Volatile
        private var instance: NoteRepositoryImpl? = null

        fun getInstance(
            noteDbDataSource: NoteDbDataSource,
            appExecutors: AppExecutors
        ): NoteRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: NoteRepositoryImpl(noteDbDataSource, appExecutors)
            }
    }

}