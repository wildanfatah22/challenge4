package com.example.challenge4.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge4.R
import com.example.challenge4.data.datasource.local.room.entity.NoteEntity
import com.example.challenge4.domain.model.Note
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteAdapter: ListAdapter<NoteEntity, NoteAdapter.ViewHolder>(NoteDiffCallback()) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(noteEntity : NoteEntity) {
            val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
            val tvDesc = itemView.findViewById<TextView>(R.id.tv_desc)
            val tvDate = itemView.findViewById<TextView>(R.id.tv_date)
            tvTitle.text = noteEntity.title
            tvDate.text = formatDateToString(noteEntity.date.toString())
            tvDesc.text = noteEntity.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            view = LayoutInflater.from(parent.context).inflate(
                R.layout.note_item,
                parent,
                false,
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(note: Note)
        fun onEditClicked(note: Note)
        fun onDeleteClicked(note: Note)
    }

    companion object {
        @JvmStatic
        fun formatDateToString(dateString: String): String {
            val inputDateFormat =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val outputDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            val date: Date?
            var outputDate = ""

            try {
                date = inputDateFormat.parse(dateString)
                outputDate = outputDateFormat.format(date!!)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return outputDate
        }
    }
}