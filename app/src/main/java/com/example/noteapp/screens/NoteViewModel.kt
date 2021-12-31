package com.example.noteapp.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.NoteDataSource
import com.example.noteapp.models.Note
import com.example.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    private val _buttonStateStore = mutableMapOf("ADD" to "Add Note", "UPDATE" to "Update Note")
    private val _notesList = MutableStateFlow<List<Note>>(emptyList())


    val notesList = _notesList.asStateFlow()

    private val _buttonState = mutableStateOf(_buttonStateStore["ADD"])

    var buttonState = _buttonState.value


    init {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.getAllNotes().distinctUntilChanged().collect { listOfNotes ->
                if (listOfNotes.isNullOrEmpty()) {
                    _notesList.value = listOfNotes
                    Log.d("NoteViewModel-Repository", "Empty List")
                } else {
                    _notesList.value = listOfNotes
                }
            }
        }
    }

    fun getNoteById(id: String) = viewModelScope.launch { noteRepository.getNoteById(id) }

    fun addNote(note: Note) = viewModelScope.launch { noteRepository.addNote(note) }

    fun updateNote(note: Note) = viewModelScope.launch { noteRepository.updateNote(note) }

    fun deleteNote(note: Note) = viewModelScope.launch { noteRepository.deleteNote(note) }

    fun updateButtonState(state: String) {
        if (_buttonStateStore.containsKey(state)) {
            _buttonState.value = _buttonStateStore[state]
        }
    }

}
