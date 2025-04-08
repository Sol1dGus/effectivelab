package com.example.marvelheroes.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.apache.commons.codec.digest.DigestUtils
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarvelApiClient {
    private const val BASE_URL = "https://gateway.marvel.com/"
    private const val MARVEL_PRIVATE_KEY = "228"
    private const val MARVEL_PUBLIC_KEY = "228"

    @Singleton
    val api: MarvelApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MarvelApi::class.java)
    }

    fun generateHash(timestamp: String): String {
        val input = timestamp + MARVEL_PRIVATE_KEY + MARVEL_PUBLIC_KEY
        return DigestUtils.md5Hex(input)
    }
}
