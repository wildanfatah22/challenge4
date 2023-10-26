package com.example.challenge4.data.local.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.challenge4.data.Note
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Note")
@Parcelize
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "sub_title")
    var subTitle:String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "date")
    var date: String? = null,

    @ColumnInfo(name = "color")
    var color:String? = null


) : Parcelable

fun NoteEntity.toNote(): Note {
    return Note(
        id = this.id,
        title= this.title,
        subTitle = this.subTitle,
        description = this.description,
        date = this.date,
        color = this.color
    )
}

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = this.id,
        title= this.title,
        subTitle = this.subTitle,
        description = this.description,
        date = this.date,
        color = this.color
    )
}
