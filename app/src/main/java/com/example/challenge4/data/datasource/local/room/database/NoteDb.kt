package com.example.challenge4.data.datasource.local.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.challenge4.data.datasource.local.room.dao.NoteDao
import com.example.challenge4.data.datasource.local.room.dao.UserDao
import com.example.challenge4.data.datasource.local.room.entity.NoteEntity
import com.example.challenge4.data.datasource.local.room.entity.UserEntity

@Database(entities = [NoteEntity::class, UserEntity::class], version = 1)
abstract class NoteDb : RoomDatabase() {
    abstract fun noteDao() : NoteDao
    abstract fun userDao(): UserDao

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