package com.example.starwarsproject.di.modules

import android.content.Context
import com.example.starwarsproject.data.StarWarsApi
import com.example.starwarsproject.data.dao.HeroDao
import com.example.starwarsproject.data.dao.PlanetDao
import com.example.starwarsproject.data.dao.SpaceshipDao
import com.example.starwarsproject.domain.MainRepository
import com.example.starwarsproject.util.PreferenceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext appContext: Context) = PreferenceProvider(appContext)

    @Singleton
    @Provides
    fun provideMainRepository(starWarsApi: StarWarsApi,
                              preferenceProvider: PreferenceProvider,
                              heroDao: HeroDao,
                              planetDao: PlanetDao,
                              spaceshipDao: SpaceshipDao
    ) = MainRepository(starWarsApi,preferenceProvider,heroDao,planetDao,spaceshipDao)
}