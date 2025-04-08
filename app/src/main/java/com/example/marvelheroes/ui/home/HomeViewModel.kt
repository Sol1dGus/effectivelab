package com.example.marvelheroes.ui.home

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
        object Error : HeroesUiState
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
                _uiState = HeroesUiState.Error
            }
        }
    }
}
