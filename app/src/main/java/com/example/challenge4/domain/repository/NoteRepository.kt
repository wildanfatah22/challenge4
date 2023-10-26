package com.example.challenge4.domain.repository

import com.example.challenge4.data.local.room.entity.NoteEntity

interface NoteRepository {
    suspend fun getAllNotes(): List<NoteEntity>
    suspend fun insertNote(noteEntity: NoteEntity): List<NoteEntity>
    suspend fun updateNote(noteEntity: NoteEntity): List<NoteEntity>
    suspend fun deleteNote(noteEntity: NoteEntity): List<NoteEntity>
    fun clearToken()
}