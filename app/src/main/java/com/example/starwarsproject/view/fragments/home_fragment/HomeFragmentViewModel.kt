package com.example.starwarsproject.view.fragments.home_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsproject.data.ApiResponse
import com.example.starwarsproject.domain.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import com.example.starwarsproject.base.Result
import com.example.starwarsproject.data.entity.Hero
import com.example.starwarsproject.data.entity.Planet
import com.example.starwarsproject.data.entity.Spaceship
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    fun getData(query: String): Flow<Result<ApiResponse<*>>> = flow {
        emit(Result.Loading)
        val result = repository.getDataWithQuery(query)
        emit(result)
    }.flowOn(Dispatchers.IO)

    fun insertHero(hero: Hero) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertHero(hero)
        }
    }

    fun insertPlanet(planet: Planet) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPlanet(planet)
        }
    }

    fun insertSpaceship(spaceship: Spaceship) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertSpaceship(spaceship)
        }
    }
}