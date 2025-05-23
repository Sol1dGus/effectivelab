package com.example.marvelheroes.data.models

object CharacterMapper {
    fun dtoToUI(dto: CharacterDto, language: String = "en"): CharacterUI {
        return CharacterUI(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            thumbnailUrl = "${dto.thumbnail.path.replace("http://", "https://")}.${dto.thumbnail.extension}"
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

    fun dtoToEntity(dto: CharacterDto, language: String = "en"): CharacterEntity {
        return CharacterEntity(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            thumbnailUrl = "${dto.thumbnail.path.replace("http://", "https://")}.${dto.thumbnail.extension}"
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
