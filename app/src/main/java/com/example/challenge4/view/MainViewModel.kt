package com.example.challenge4.view

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge4.model.local.repository.NoteRepository
import com.example.challenge4.model.local.room.Note

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(application: Application, sharedPreferences: SharedPreferences) : ViewModel() {
    private val noteRepository: NoteRepository = NoteRepository(application, sharedPreferences)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _notes = MutableLiveData<List<Note>>()
    val notes : LiveData<List<Note>> = _notes

    fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            delay(1000)
            _notes.postValue(noteRepository.getAllNotes())
        }
    }

    fun insert(note: Note) {
        noteRepository.insert(note)
    }
    fun update(note: Note) {
        noteRepository.update(note)
    }
    fun delete(note: Note) {
        noteRepository.delete(note)
    }
}