package com.example.marvelheroes.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarvelResponse(
    @Json(name = "data")
    val data: CharacterDataContainer
)

@JsonClass(generateAdapter = true)
data class CharacterDataContainer(
    @Json(name = "results")
    val results: List<CharacterDto>
)

@JsonClass(generateAdapter = true)
data class CharacterDto(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
) {
    fun toCharacterUI(): CharacterUI = CharacterUI(
        id = id,
        name = name,
        description = description,
        thumbnailUrl = "${thumbnail.path.replace("http://", "https://")}.${thumbnail.extension}"
    )
}

@JsonClass(generateAdapter = true)
data class Thumbnail(
    val path: String,
    val extension: String
)