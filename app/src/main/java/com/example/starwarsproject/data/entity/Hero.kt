package com.example.starwarsproject.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroes")
data class Hero(
    @PrimaryKey
    val name: String,
    val birth_year: String,
    val created: String,
    val edited: String,
    val eye_color: String,
    val films: List<String>,
    val gender: String,
    val hair_color: String,
    val height: String,
    val homeworld: String,
    val mass: String,

    val skin_color: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
)