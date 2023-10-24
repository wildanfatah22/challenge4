package com.example.challenge4.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.challenge4.model.local.room.Note

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

}