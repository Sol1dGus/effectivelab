package com.example.marvelheroes.data.repository

import com.example.marvelheroes.data.api.MarvelApi
import com.example.marvelheroes.data.api.MarvelApiClient
import com.example.marvelheroes.data.models.Hero
import javax.inject.Inject

class HeroRepository @Inject constructor(
    private val api: MarvelApi
) {
    private val MARVEL_PRIVATE_KEY = "228"
    private val MARVEL_PUBLIC_KEY = "228"

    suspend fun getHeroes(): List<Hero> {
        val timestamp = System.currentTimeMillis().toString()
        val hash = MarvelApiClient.generateHash(timestamp)
        val response = api.getCharacters(
            apiKey = MARVEL_PUBLIC_KEY,
            timestamp = timestamp,
            hash = hash
        )
        return response.data.results.map { it.toHero() }
    }

    suspend fun getHeroById(id: Int): Hero {
        val timestamp = System.currentTimeMillis().toString()
        val response = api.getCharacterById(
            id = id,
            apiKey = MARVEL_PUBLIC_KEY,
            timestamp = timestamp,
            hash = MarvelApiClient.generateHash(timestamp)
        )
        return response.data.results.first().toHero()
    }
}