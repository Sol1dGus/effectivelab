package com.example.marvelheroes.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.marvelheroes.R
import com.example.marvelheroes.data.models.Hero
import com.example.marvelheroes.ui.loading.LoadingScreen
import com.example.marvelheroes.ui.error.ErrorScreen
import com.example.marvelheroes.ui.theme.invisible
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

const val TIME_TO_SECOND_CLICK = 500

@Composable
fun DetailsScreen(
    navController: NavController,
    heroId: Int?,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    LaunchedEffect(heroId) {
        viewModel.getHero(heroId)
    }

    val uiState = viewModel.uiState;
    when (uiState) {
        is DetailsViewModel.HeroUiState.Loading -> LoadingScreen()
        is DetailsViewModel.HeroUiState.Error -> ErrorScreen(uiState.message)
        is DetailsViewModel.HeroUiState.Success -> SuccessScreen(navController, uiState.hero)
    }
}


@Composable
fun SuccessScreen(navController: NavController, hero: Hero) {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    )
    {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(hero.imageUrl)
                .build(),
            placeholder = painterResource(R.drawable.loading),
            modifier = Modifier
                .fillMaxHeight(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            error = painterResource(R.drawable.error)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 36.dp),
            colors = CardDefaults.cardColors(
                containerColor = invisible
            )
        ) {
            Column(
            ) {
                Text(
                    text = hero.name,
                    style = TextStyle(
                        fontSize = 48.sp,
                        lineHeight = 64.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    ),
                    color = Color.White,
                    modifier = Modifier.padding(8.dp),
                )

                Text(
                    text = hero.description,
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
        )
        {
            Button(
                onClick = {
                    val currentTime = System.currentTimeMillis()
                    // Time between 2 clicks
                    if (currentTime - lastClickTime > TIME_TO_SECOND_CLICK) {
                        lastClickTime = currentTime
                        navController.popBackStack()
                    }
                },
                colors = ButtonColors(
                    containerColor = invisible,
                    contentColor = Color.White,
                    disabledContainerColor = invisible,
                    disabledContentColor = invisible
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
    }
}