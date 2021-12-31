package com.example.noteapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.data.NoteDao
import com.example.noteapp.data.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesNoteDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(context, NoteDatabase::class.java, "notes")
            .fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun providesNoteDao(noteDatabase: NoteDatabase): NoteDao = noteDatabase.noteDao()

}