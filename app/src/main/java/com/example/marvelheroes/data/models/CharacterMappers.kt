package com.example.marvelheroes.data.models

import com.example.marvelheroes.data.api.CharacterDto

object CharacterMapper {

    fun dtoToUI(dto: CharacterDto): CharacterUI {
        return CharacterUI(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            thumbnailUrl = "${dto.thumbnail.path}.${dto.thumbnail.extension}"
        )
    }

    fun entityToUI(entity: CharacterEntity): CharacterUI {
        return CharacterUI(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            thumbnailUrl = entity.thumbnailUrl
        )
    }

    fun dtoToEntity(dto: CharacterDto): CharacterEntity {
        return CharacterEntity(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            thumbnailUrl = "${dto.thumbnail.path}.${dto.thumbnail.extension}"
        )
    }

    fun uiToEntity(ui: CharacterUI): CharacterEntity {
        return CharacterEntity(
            id = ui.id,
            name = ui.name,
            description = ui.description,
            thumbnailUrl = ui.thumbnailUrl
        )
    }
}