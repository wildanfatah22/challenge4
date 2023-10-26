package com.example.challenge4.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge4.data.local.repository.LocalRepository
import com.example.challenge4.data.local.room.entity.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainViewModel(private val localRepository: LocalRepository) : ViewModel() {


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _notes = MutableLiveData<List<NoteEntity>>()
    val notes: LiveData<List<NoteEntity>> = _notes

//    fun getAllNotes() {
//        viewModelScope.launch(Dispatchers.IO) {
//            _isLoading.postValue(true)
//            delay(1000)
//            _notes.postValue(localRepository.getAllNotes())
//            _isLoading.postValue(false)
//        }
//    }

//    fun insert(note: Note) {
//        viewModelScope.launch(Dispatchers.IO) {
//            _notes.postValue(localRepository.insert(note))
//        }
//    }
//
//    fun update(note: Note) {
//        viewModelScope.launch(Dispatchers.IO) {
//            _notes.postValue(localRepository.update(note))
//        }
//    }
//
//    fun delete(note: Note) {
//        viewModelScope.launch(Dispatchers.IO) {
//            _notes.postValue(localRepository.delete(note))
//        }
//    }

}