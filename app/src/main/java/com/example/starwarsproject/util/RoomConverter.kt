package com.example.starwarsproject.util

import androidx.room.TypeConverter

class RoomConverter {
    @TypeConverter
    fun fromListOfIntsToString(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun fromStringTOListOfInts(str: String): List<String> {
        return if (str == "")
            emptyList()
        else
            str.split(",").toTypedArray().map { it.toString() }
    }
}