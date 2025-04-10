package com.example.marvelheroes.data.api

import com.example.marvelheroes.data.models.Hero
import com.squareup.moshi.Json

data class MarvelResponse(
    @Json(name = "data")
    val data: CharacterDataContainer
)

data class CharacterDataContainer(
    @Json(name = "results")
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
        imageUrl = "${thumbnail.path.replace("http", "https")}.${thumbnail.extension}"
    )
}

data class Thumbnail(
    val path: String,
    val extension: String
)