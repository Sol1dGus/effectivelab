package com.example.marvelheroes.ui.details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import com.example.marvelheroes.data.repository.HeroRepository
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.data.models.CharacterUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {
    sealed interface HeroUiState {
        data class Success(val characterUI: CharacterUI) : HeroUiState
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
                    val characterUI = repository.getHeroById(id)
                    Log.d("HeroImage", characterUI.thumbnailUrl)
                    _uiState = HeroUiState.Success(characterUI)
                } catch (e: Exception) {
                    _uiState = HeroUiState.Error(e.localizedMessage ?: "Unknown problem")
                }
            }
        }
    }
}