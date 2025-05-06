package com.example.marvelheroes.data.repository

import com.example.marvelheroes.BuildConfig
import com.example.marvelheroes.data.api.MarvelApi
import com.example.marvelheroes.data.api.MarvelHashHelper
import com.example.marvelheroes.data.db.CharacterDao
import com.example.marvelheroes.data.models.CharacterMapper
import com.example.marvelheroes.data.models.CharacterUI
import javax.inject.Inject

class HeroRepository @Inject constructor(
    private val api: MarvelApi,
    private val hashGenerator: MarvelHashHelper,
    private val dao: CharacterDao
) {
    suspend fun getHeroesByIds(ids: List<Int>): List<CharacterUI> {
        var characterUIList : MutableList<CharacterUI> = mutableListOf()
        ids.forEach { id ->
            characterUIList.add(getHeroById(id))
        }
        return characterUIList
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