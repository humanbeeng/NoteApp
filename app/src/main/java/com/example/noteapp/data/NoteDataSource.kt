package com.example.noteapp.data

import com.example.noteapp.models.Note

class NoteDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "Grocery", text = "Go to More"),
            Note(title = "Todo", text = "Learn android")
        )
    }
}