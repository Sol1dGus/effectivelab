package com.example.marvelheroes.data.api

import com.example.marvelheroes.data.models.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MarvelApi {
    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") id: Int,
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String
    ): MarvelResponse
}