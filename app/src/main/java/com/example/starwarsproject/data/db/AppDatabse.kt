package com.example.starwarsproject.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.starwarsproject.data.dao.HeroDao
import com.example.starwarsproject.data.dao.PlanetDao
import com.example.starwarsproject.data.dao.SpaceshipDao
import com.example.starwarsproject.data.entity.Hero
import com.example.starwarsproject.data.entity.Planet
import com.example.starwarsproject.data.entity.Spaceship
import com.example.starwarsproject.util.RoomConverter

@Database(
    entities = [Hero::class, Planet::class, Spaceship::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun planetDao(): PlanetDao
    abstract fun spaceshipDao(): SpaceshipDao
}