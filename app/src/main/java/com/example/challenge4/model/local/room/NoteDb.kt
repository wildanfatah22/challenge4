package com.example.challenge4.model.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDb : RoomDatabase() {
    abstract fun noteDao() : NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDb? = null
        @JvmStatic
        fun getDatabase(context: Context): NoteDb {
            if (INSTANCE == null) {
                synchronized(NoteDb::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NoteDb::class.java, "note_database")
                        .build()
                }
            }
            return INSTANCE as NoteDb
        }
    }
}