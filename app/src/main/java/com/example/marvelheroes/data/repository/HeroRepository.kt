package com.example.marvelheroes.data.repository

import com.example.marvelheroes.BuildConfig
import com.example.marvelheroes.data.api.MarvelApi
import com.example.marvelheroes.data.api.MarvelHashHelper
import com.example.marvelheroes.data.db.CharacterDao
import com.example.marvelheroes.data.models.CharacterMapper
import com.example.marvelheroes.data.models.CharacterUI
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class HeroRepository @Inject constructor(
    private val api: MarvelApi,
    private val hashGenerator: MarvelHashHelper,
    private val dao: CharacterDao
) {
    suspend fun getHeroesByIds(ids: List<Int>): List<CharacterUI> = coroutineScope {
        ids.map { id ->
            async {
                try {
                    getHeroById(id) // Предположительно это suspend-функция, возвращающая CharacterUI
                } catch (e: Exception) {
                    null // Можно залогировать или вернуть placeholder
                }
            }
        }.awaitAll()
            .filterNotNull() // Удаляем неудачные запросы
    }

    suspend fun getHeroById(id: Int): CharacterUI {
        val local = dao.getCharacterById(id)
        if (local != null) {
            return CharacterMapper.entityToUI(local)
        }

        val timestamp = System.currentTimeMillis().toString()
        val response = api.getCharacterById(
            id = id,
            apiKey = BuildConfig.MARVEL_PUBLIC_KEY,
            timestamp = timestamp,
            hash = hashGenerator.generateHash(timestamp)
        )
        val hero = response.data.results.first().toCharacterUI()

        dao.insertCharacter(CharacterMapper.uiToEntity(hero))

        return hero
    }
}