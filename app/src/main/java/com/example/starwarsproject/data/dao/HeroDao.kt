package com.example.starwarsproject.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarsproject.data.entity.Hero

@Dao
interface HeroDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(hero: Hero)

    @Query("SELECT * FROM heroes")
    suspend fun getAllHeroes(): List<Hero>
}

