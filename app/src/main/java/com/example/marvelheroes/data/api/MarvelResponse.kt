package com.example.marvelheroes.data.api

import com.example.marvelheroes.data.models.Hero

data class MarvelResponse(
    val data: CharacterDataContainer
)

data class CharacterDataContainer(
    val results: List<CharacterDto>
)

data class CharacterDto(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
) {
    fun toHero(): Hero = Hero(
        id = id,
        name = name,
        description = description,
        imageUrl = "${thumbnail.path}.${thumbnail.extension}"
    )
}

data class Thumbnail(
    val path: String,
    val extension: String
)