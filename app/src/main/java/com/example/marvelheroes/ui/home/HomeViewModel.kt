package com.example.marvelheroes.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.api.MarvelApi
import com.example.marvelheroes.data.api.MarvelApiClient
import com.example.marvelheroes.data.models.Hero
import com.example.marvelheroes.data.repository.HeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel()
{

sealed interface HeroesUiState {
        data class Success(val heroes: List<Hero>) : HeroesUiState
        class Error(val message: String) : HeroesUiState
        object Loading : HeroesUiState
    }

    private var _uiState by mutableStateOf<HeroesUiState>(HeroesUiState.Loading)
    val uiState: HeroesUiState get() = _uiState

    init {
        getHeroes()
    }

    fun getHeroes() {
        viewModelScope.launch {
            _uiState = HeroesUiState.Loading
            try {
                val heroes = repository.getHeroes()
                _uiState = HeroesUiState.Success(heroes)
            } catch (e: Exception) {
                Log.e("HomeViewModel", e.localizedMessage ?: "Unknown problem")
                _uiState = HeroesUiState.Error(e.localizedMessage ?: "Unknown problem")
            }
        }
    }
}
