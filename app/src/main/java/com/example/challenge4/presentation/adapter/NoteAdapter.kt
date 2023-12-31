package com.example.challenge4.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge4.databinding.NoteItemBinding
import com.example.challenge4.domain.model.Note


class NoteAdapter(private val note: List<Note>): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    class ViewHolder(private val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            val tvTitle = binding.tvTitle
            val tvDesc = binding.tvDesc
            val tvDate = binding.tvDate
            tvTitle.text = note.title
            tvDate.text = note.date
            tvDesc.text = note.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = note.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = note[position]
        holder.bind(note)
        
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(note)
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(note: Note)
    }

}