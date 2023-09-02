package com.example.starwarsproject.data

import com.example.starwarsproject.data.entity.Hero
import com.example.starwarsproject.data.entity.Planet
import com.example.starwarsproject.data.entity.Spaceship
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApi {
    @GET("people/")
    suspend fun searchPeople(@Query("search") query: String): Response<ApiResponse<Hero>>

    @GET("starships/")
    suspend fun searchSpaceships(@Query("search") query: String): Response<ApiResponse<Spaceship>>

    @GET("planets/")
    suspend fun searchPlanets(@Query("search") query: String): Response<ApiResponse<Planet>>
}