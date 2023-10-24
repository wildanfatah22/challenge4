package com.example.challenge4.model.local.repository

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.example.challenge4.model.local.room.Note
import com.example.challenge4.model.local.room.NoteDao
import com.example.challenge4.model.local.room.NoteDb
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application, sharedPreferences: SharedPreferences) {
    private val noteDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteDb.getDatabase(application)
        noteDao = db.noteDao()
    }

    fun getAllNotes(): List<Note> = noteDao.getAllNotes()

    fun insert(note: Note) {
        executorService.execute { noteDao.insert(note) }
    }

    fun delete(note: Note) {
        executorService.execute { noteDao.delete(note) }
    }
    fun update(note: Note) {
        executorService.execute { noteDao.update(note) }
    }

}