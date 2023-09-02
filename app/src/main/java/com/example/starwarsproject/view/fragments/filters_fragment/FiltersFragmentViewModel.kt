package com.example.starwarsproject.view.fragments.filters_fragment

import androidx.lifecycle.ViewModel
import com.example.starwarsproject.domain.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FiltersFragmentViewModel@Inject constructor(private val repository: MainRepository): ViewModel() {
    private var _currentCategory = MutableStateFlow("")
    val currentCategory: StateFlow<String> = _currentCategory.asStateFlow()

    init {
        _currentCategory.value = repository.getDefaultCategoryFromPreferences()
    }

    fun saveDefaultCategory(category: String) {
        repository.saveDefaultCategoryToPreferences(category)
    }
}