package com.example.marvelheroes.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "characters", primaryKeys = ["id"])
data class CharacterEntity(val id: Int,
    val name: String,
    val description: String,
    val thumbnailUrl: String
)