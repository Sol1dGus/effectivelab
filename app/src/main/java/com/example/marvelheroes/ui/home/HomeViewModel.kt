package com.example.marvelheroes.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.models.CharacterUI
import com.example.marvelheroes.data.repository.HeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.internal.immutableListOf
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {

    sealed interface HeroesUiState {
        data class Success(val characterUIS: List<CharacterUI>) : HeroesUiState
        class Error(val message: String) : HeroesUiState
        object Loading : HeroesUiState
    }

    private var _uiState by mutableStateOf<HeroesUiState>(HeroesUiState.Loading)
    val uiState: HeroesUiState get() = _uiState

    val heroIds = immutableListOf(
        1009610, // Spider-Man
        1009368, // Iron Man
        1009220, // Captain America
        1009664, // Thor
        1009351, // Hulk
        1009189, // Black Widow
        1009718, // Wolverine
        1009268, // Deadpool
        1009282, // Doctor Strange
        1009338, // Hawkeye
        1011334, // Vision
        1017100  // Scarlet Witch
    )

    init {
        getHeroesByIds()
    }

    fun getHeroesByIds() {
        viewModelScope.launch {
            _uiState = HeroesUiState.Loading
            try {
                val characterUIS = repository.getHeroesByIds(heroIds)
                _uiState = HeroesUiState.Success(characterUIS)
            } catch (e: Exception) {
                Log.e("HomeViewModel", e.localizedMessage ?: "Unknown problem")
                _uiState = HeroesUiState.Error(e.localizedMessage ?: "Unknown problem")
            }
        }
    }
}
