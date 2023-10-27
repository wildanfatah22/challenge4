package com.example.challenge4.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challenge4.data.repository.NoteRepositoryImpl
import com.example.challenge4.data.datasource.local.room.entity.NoteEntity
import com.example.challenge4.domain.model.Note
import com.example.challenge4.domain.usecase.NoteUseCase


class MainViewModel(private val noteUseCase: NoteUseCase) : ViewModel() {

    val getAllNote = noteUseCase.getAllNote()

    fun deleteNote(note: Note) {
        noteUseCase.deleteNote(note)
    }
}