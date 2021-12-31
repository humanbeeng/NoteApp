package com.example.noteapp.repository

import com.example.noteapp.models.Note
import com.example.noteapp.data.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    fun getAllNotes(): Flow<List<Note>> =
        noteDao.getNotes().flowOn(context = Dispatchers.IO).conflate()

    suspend fun getNoteById(id: String) = noteDao.getNoteById(id)

    suspend fun addNote(note: Note) = noteDao.insertNote(note)

    suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

}