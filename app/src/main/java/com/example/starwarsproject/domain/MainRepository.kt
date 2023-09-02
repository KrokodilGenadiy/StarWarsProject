package com.example.starwarsproject.domain

import com.example.starwarsproject.data.ApiResponse
import com.example.starwarsproject.data.StarWarsApi
import com.example.starwarsproject.util.PreferenceProvider
import com.example.starwarsproject.base.Result
import com.example.starwarsproject.data.dao.HeroDao
import com.example.starwarsproject.data.dao.PlanetDao
import com.example.starwarsproject.data.dao.SpaceshipDao
import com.example.starwarsproject.data.entity.Hero
import com.example.starwarsproject.data.entity.Planet
import com.example.starwarsproject.data.entity.Spaceship
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


class MainRepository(
    private val retrofitService: StarWarsApi,
    private val preferences: PreferenceProvider,
    private val heroDao: HeroDao,
    private val planetDao: PlanetDao,
    private val spaceshipDao: SpaceshipDao
) {
    suspend fun getDataWithQuery(query: String): Result<ApiResponse<*>> {
        return try {
            val response: Response<out ApiResponse<*>> = when (preferences.getDefaultCategory()) {
                "People" -> {
                    retrofitService.searchPeople(query)
                }

                "Spaceships" -> {
                    retrofitService.searchSpaceships(query)
                }

                "Planets" -> {
                    retrofitService.searchPlanets(query)
                }

                else -> {
                    throw IllegalArgumentException("Wrong query type")
                }
            }
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(response.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "An error occurred")
        }
    }
    // Insert methods for each entity
    suspend fun insertHero(hero: Hero) {
        heroDao.insert(hero)
    }

    suspend fun insertPlanet(planet: Planet) {
        planetDao.insert(planet)
    }

    suspend fun insertSpaceship(spaceship: Spaceship) {
        spaceshipDao.insert(spaceship)
    }

    suspend fun getAllHeroes(): List<Hero> {
        return heroDao.getAllHeroes()
    }

    suspend fun getAllPlanets(): List<Planet> {
        return planetDao.getAllPlanets()
    }

    suspend fun getAllSpaceships(): List<Spaceship> {
        return spaceshipDao.getAllSpaceships()
    }
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()
}