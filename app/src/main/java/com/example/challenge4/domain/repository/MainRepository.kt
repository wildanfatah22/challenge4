package com.example.challenge4.domain.repository

import com.example.challenge4.data.local.room.entity.NoteEntity

interface MainRepository {
    suspend fun getAllNotes(): List<NoteEntity>
    suspend fun insert(noteEntity: NoteEntity): List<NoteEntity>
    suspend fun update(noteEntity: NoteEntity): List<NoteEntity>
    suspend fun delete(noteEntity: NoteEntity): List<NoteEntity>
    fun clearToken()
}