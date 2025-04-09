package com.example.marvelheroes.data.api

import com.example.marvelheroes.BuildConfig
import org.apache.commons.codec.digest.DigestUtils
import javax.inject.Inject

class MarvelHashHelper @Inject constructor() {
    fun generateHash(timestamp: String): String {
        val input = timestamp + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_PUBLIC_KEY
        return DigestUtils.md5Hex(input)
    }
}