package com.example.challenge4.data.datasource.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.challenge4.data.datasource.local.room.entity.NoteEntity

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(noteEntity: NoteEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNote(noteEntity: NoteEntity)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)

    @Query("SELECT * from note ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM note WHERE id =:id")
    fun getSelectedNote(id: Int): LiveData<NoteEntity>
}