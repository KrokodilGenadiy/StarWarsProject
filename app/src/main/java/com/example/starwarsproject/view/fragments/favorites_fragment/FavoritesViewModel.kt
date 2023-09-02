package com.example.starwarsproject.view.fragments.favorites_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsproject.base.Result
import com.example.starwarsproject.data.ApiResponse
import com.example.starwarsproject.data.entity.Hero
import com.example.starwarsproject.data.entity.Planet
import com.example.starwarsproject.data.entity.SectionItem
import com.example.starwarsproject.data.entity.Spaceship
import com.example.starwarsproject.domain.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    fun getData(): Flow<List<SectionItem<Any>>> = flow {
        val sections: MutableList<SectionItem<Any>> = mutableListOf()
        val heroes = repository.getAllHeroes()
        val spaceships = repository.getAllSpaceships()
        val planets = repository.getAllPlanets()
        if (heroes.isNotEmpty()) {
            sections.add(SectionItem("Characters", heroes))
        }
        if (spaceships.isNotEmpty()) {
            sections.add(SectionItem("Spaceships", spaceships))
        }
        if (planets.isNotEmpty()) {
            sections.add(SectionItem("Planets", planets))
        }
        emit(sections)
    }.flowOn(Dispatchers.IO)
}