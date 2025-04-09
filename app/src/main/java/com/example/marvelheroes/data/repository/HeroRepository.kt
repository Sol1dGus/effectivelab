package com.example.marvelheroes.data.repository

import com.example.marvelheroes.BuildConfig
import com.example.marvelheroes.data.api.MarvelApi
import com.example.marvelheroes.data.api.MarvelApiClient
import com.example.marvelheroes.data.api.MarvelHashHelper
import com.example.marvelheroes.data.models.Hero
import javax.inject.Inject

class HeroRepository @Inject constructor(
    private val api: MarvelApi,
    private val hashGenerator: MarvelHashHelper
) {
    suspend fun getHeroes(): List<Hero> {
        val timestamp = System.currentTimeMillis().toString()
        val hash = hashGenerator.generateHash(timestamp)
        val response = api.getCharacters(
            apiKey = BuildConfig.MARVEL_PUBLIC_KEY,
            timestamp = timestamp,
            hash = hash
        )
        return response.data.results.map { it.toHero() }
    }

    suspend fun getHeroById(id: Int): Hero {
        val timestamp = System.currentTimeMillis().toString()
        val response = api.getCharacterById(
            id = id,
            apiKey = BuildConfig.MARVEL_PUBLIC_KEY,
            timestamp = timestamp,
            hash = hashGenerator.generateHash(timestamp)
        )
        return response.data.results.first().toHero()
    }
}