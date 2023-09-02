package com.example.starwarsproject.di.modules

import android.content.Context
import androidx.room.Room
import com.example.starwarsproject.data.dao.HeroDao
import com.example.starwarsproject.data.dao.PlanetDao
import com.example.starwarsproject.data.dao.SpaceshipDao
import com.example.starwarsproject.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Use the appropriate component scope
object DatabaseModule {

    @Provides
    @Singleton // If you want a singleton instance of the database
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database"
        ).build()
    }

    @Provides
    fun provideHeroDao(database: AppDatabase): HeroDao {
        return database.heroDao()
    }

    @Provides
    fun providePlanetDao(database: AppDatabase): PlanetDao {
        return database.planetDao()
    }

    @Provides
    fun provideSpaceshipDao(database: AppDatabase): SpaceshipDao {
        return database.spaceshipDao()
    }
}