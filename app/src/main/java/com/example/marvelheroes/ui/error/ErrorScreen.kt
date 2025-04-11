package com.example.marvelheroes.ui.error

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorScreen(message: String = "Ошибка") {
    Text(message)
}
