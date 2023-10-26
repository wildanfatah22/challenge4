package com.example.challenge4.data.datasource.local

import androidx.lifecycle.LiveData
import com.example.challenge4.data.datasource.local.room.dao.NoteDao
import com.example.challenge4.data.datasource.local.room.entity.NoteEntity

interface NoteDataSource {
    fun insertNote(noteEntity: NoteEntity)

    fun deleteNote(noteEntity: NoteEntity)

    fun updateNote(noteEntity: NoteEntity)

    fun getAllNote(): LiveData<List<NoteEntity>>

    fun getSelectedNote(playerId: Int): LiveData<NoteEntity>
}

class NoteDbDataSource(private val noteDao: NoteDao) :
    NoteDataSource {

    companion object {
        private var instance: NoteDbDataSource? = null

        fun getInstance(noteDao: NoteDao): NoteDbDataSource =
            instance ?: synchronized(this) {
                instance ?: NoteDbDataSource(noteDao)
            }
    }

    override fun insertNote(noteEntity: NoteEntity) {
        noteDao.insertNote(noteEntity)
    }

    override fun deleteNote(noteEntity: NoteEntity) {
        noteDao.deleteNote(noteEntity)
    }

    override fun updateNote(noteEntity: NoteEntity) {
        noteDao.updateNote(noteEntity)
    }

    override fun getAllNote(): LiveData<List<NoteEntity>> {
        return noteDao.getAllNotes()
    }

    override fun getSelectedNote(id: Int): LiveData<NoteEntity> {
        return noteDao.getSelectedNote(id)
    }
}