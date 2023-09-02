package com.example.starwarsproject.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planets")
data class Planet(
    @PrimaryKey
    val name: String,
    val climate: String,
    val created: String,
    val diameter: String,
    val edited: String,
    val films: List<String>,
    val gravity: String,

    val orbital_period: String,
    val population: String,
    val residents: List<String>,
    val rotation_period: String,
    val surface_water: String,
    val terrain: String,
    val url: String
)