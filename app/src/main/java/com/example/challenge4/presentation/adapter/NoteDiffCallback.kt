package com.example.challenge4.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.challenge4.data.datasource.local.room.entity.NoteEntity

class NoteDiffCallback : DiffUtil.ItemCallback<NoteEntity>() {
    override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem == newItem
    }

}