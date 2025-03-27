package com.example.marvelheroes.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.marvelheroes.R
import com.example.marvelheroes.data.models.Hero

@Composable
fun HeroCard(
    modifier: Modifier = Modifier,
    hero: Hero,
    onClick: (Int) -> Unit
){
    OutlinedCard(
        modifier = modifier
            .fillMaxHeight()
            .clickable { onClick(hero.id) }
            .padding(horizontal = 28.dp)
            .padding(vertical = 36.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        border = BorderStroke(1.dp, Color.Red),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        )
        {
            //Изображение героя
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(hero.imageUrl)
                    .build(),
                placeholder = painterResource(R.drawable.loading),
                modifier = Modifier
                    .fillMaxSize(),
                contentDescription = null,
                error = painterResource(R.drawable.error),
                contentScale = ContentScale.Crop
            )
            //Имя героя
            Text(
                text = hero.name,
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp),
                fontSize = 36.sp
            )
        }
    }
}