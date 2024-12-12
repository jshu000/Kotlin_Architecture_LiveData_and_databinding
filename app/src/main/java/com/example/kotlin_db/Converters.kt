package com.example.kotlin_db

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    @TypeConverter
    fun fromDatetoLong(date:Date): Long {
        return date.time
    }

    @TypeConverter
    fun fromLongtoDate(value:Long): Date {
        return Date(value)
    }
}