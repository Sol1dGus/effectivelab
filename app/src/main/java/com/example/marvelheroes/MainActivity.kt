package com.example.marvelheroes

import com.example.marvelheroes.navigation.Nav
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvelheroes.ui.theme.MarvelHeroesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelHeroesTheme {
                Nav()
            }
        }
    }
}

@Preview
@Composable
fun Preview()
{
    MarvelHeroesTheme {
        Nav()
    }
}