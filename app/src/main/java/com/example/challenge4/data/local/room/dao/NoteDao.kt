package com.example.challenge4.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.challenge4.data.local.room.entity.NoteEntity

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteEntity: NoteEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(noteEntity: NoteEntity)

    @Delete
    suspend fun delete(noteEntity: NoteEntity)

    @Query("SELECT * from note ORDER BY id ASC")
    suspend fun getAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM note WHERE id =:id")
    suspend fun getSelectedNote(id:Int) : NoteEntity
}