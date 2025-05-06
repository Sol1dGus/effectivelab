package com.example.marvelheroes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelheroes.data.models.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}