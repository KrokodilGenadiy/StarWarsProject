package com.example.starwarsproject.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarsproject.data.entity.Hero
import com.example.starwarsproject.data.entity.Planet
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(planet: Planet)

    @Query("SELECT * FROM planets")
    suspend fun getAllPlanets(): List<Planet>
}
