package com.example.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noteapp.models.Note
import com.example.noteapp.util.DateConverter
import com.example.noteapp.util.UUIDConverter

@Database(entities = [Note::class], version = 2, exportSchema = false)
@TypeConverters(UUIDConverter::class, DateConverter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}