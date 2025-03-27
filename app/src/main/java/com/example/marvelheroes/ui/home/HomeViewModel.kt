package com.example.marvelheroes.ui.home

import androidx.lifecycle.ViewModel
import com.example.marvelheroes.data.repository.HeroRepository

class HomeViewModel : ViewModel() {
    private val repo = HeroRepository()
    val heroes = repo.getHeroList()
}
