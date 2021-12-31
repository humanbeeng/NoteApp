package com.example.noteapp.util

import androidx.room.TypeConverter
import java.util.*


class DateConverter {

    @TypeConverter
    fun fromDate(date: Date): Long = date.time

    @TypeConverter
    fun toDate(date: Long): Date = Date(date)

}