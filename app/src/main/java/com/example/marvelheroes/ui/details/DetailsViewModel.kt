package com.example.marvelheroes.ui.details

import androidx.lifecycle.ViewModel
import com.example.marvelheroes.data.repository.HeroRepository

class DetailsViewModel : ViewModel() {
    private val repo = HeroRepository()
    val heroes = repo.getHeroList()
}