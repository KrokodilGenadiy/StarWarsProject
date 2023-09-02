package com.example.starwarsproject.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarsproject.data.entity.Spaceship
import kotlinx.coroutines.flow.Flow

@Dao
interface SpaceshipDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(spaceship: Spaceship)

    @Query("SELECT * FROM spaceships")
    suspend fun getAllSpaceships(): List<Spaceship>
}