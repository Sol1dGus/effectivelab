package com.example.marvelheroes.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.marvelheroes.R
import com.example.marvelheroes.data.models.CharacterUI
import com.example.marvelheroes.ui.theme.cardPaddingHorizontal
import com.example.marvelheroes.ui.theme.cardPaddingVertical
import com.example.marvelheroes.ui.theme.cardSizeWidth
import com.example.marvelheroes.ui.theme.cardTextPadding
import com.example.marvelheroes.ui.theme.textColor
import com.example.marvelheroes.ui.theme.textContentSize
import com.example.marvelheroes.ui.theme.textTitleSize

@Composable
fun HeroCard(
    modifier: Modifier = Modifier,
    characterUI: CharacterUI,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxHeight()
            .width(width = cardSizeWidth)
            .clickable { onClick(characterUI.id) }
            .padding(horizontal = cardPaddingHorizontal)
            .padding(vertical = cardPaddingVertical),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        )
        {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(characterUI.thumbnailUrl)
                    .build(),
                placeholder = painterResource(R.drawable.loading),
                modifier = Modifier
                    .fillMaxSize(),
                contentDescription = null,
                error = painterResource(R.drawable.error),
                contentScale = ContentScale.Crop
            )

            Text(
                text = characterUI.name,
                style = TextStyle(fontSize = textContentSize, lineHeight = 36.sp, fontWeight = FontWeight.Bold),
                color = textColor,
                modifier = Modifier
                    .padding(cardTextPadding),
                fontSize = textTitleSize
            )
        }
    }
}