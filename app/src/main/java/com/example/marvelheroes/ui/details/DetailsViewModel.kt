package com.example.marvelheroes.ui.details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import com.example.marvelheroes.data.repository.HeroRepository
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.models.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel()
{
    sealed interface HeroUiState {
        data class Success(val hero: Hero) : HeroUiState
        class Error(val message: String) : HeroUiState
        object Loading : HeroUiState
    }

    private var _uiState by mutableStateOf<HeroUiState>(HeroUiState.Loading)
    val uiState: HeroUiState get() = _uiState

    fun getHero(id: Int?) {
        viewModelScope.launch {
            if (id != null) {
                _uiState = HeroUiState.Loading
                try {
                    val hero = repository.getHeroById(id)
                    Log.d("HeroImage", hero.imageUrl)
                    _uiState = HeroUiState.Success(hero)
                } catch (e: Exception) {
                    _uiState = HeroUiState.Error(e.localizedMessage ?: "Unknown problem")
                }
            }
        }
    }
}