package com.example.marvelheroes.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterUI(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnailUrl: String
)