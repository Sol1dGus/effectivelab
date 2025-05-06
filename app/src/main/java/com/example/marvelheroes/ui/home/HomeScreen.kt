package com.example.marvelheroes.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.marvelheroes.R
import com.example.marvelheroes.data.models.CharacterUI
import com.example.marvelheroes.ui.components.HeroCard
import com.example.marvelheroes.ui.loading.LoadingScreen
import com.example.marvelheroes.ui.error.ErrorScreen
import com.example.marvelheroes.ui.theme.homeScreenTextTopPadding
import com.example.marvelheroes.ui.theme.logoImageUrl
import com.example.marvelheroes.ui.theme.logoSizeHeight
import com.example.marvelheroes.ui.theme.logoTopPadding
import com.example.marvelheroes.ui.theme.paddingBetweenCards
import com.example.marvelheroes.ui.theme.textColor
import com.example.marvelheroes.ui.theme.textTitleSize

const val TIME_TO_SECOND_CLICK = 500

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    when (uiState) {
        is HomeViewModel.HeroesUiState.Loading -> LoadingScreen()
        is HomeViewModel.HeroesUiState.Error -> ErrorScreen(uiState.message)
        is HomeViewModel.HeroesUiState.Success -> SuccessScreen(
            navController,
            modifier,
            uiState.characterUIS
        )
    }
}

@Composable
fun SuccessScreen(navController: NavController, modifier: Modifier, characterUIS: List<CharacterUI>) {

    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
    val heroes = characterUIS
    var lastClickTime by remember { mutableLongStateOf(0L) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(logoImageUrl)
                    .build(),
                placeholder = painterResource(R.drawable.loading),
                modifier = modifier
                    .fillMaxWidth()
                    .height(height = logoSizeHeight)
                    .padding(top = logoTopPadding),
                contentDescription = null,
                error = painterResource(R.drawable.error)
            )

            Text(
                text = "Choose your hero!",
                modifier
                    .wrapContentWidth()
                    .padding(all = homeScreenTextTopPadding),
                fontWeight = FontWeight.Bold,
                fontSize = textTitleSize,
                color = textColor
            )

            LazyRow(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 12.dp),

                state = lazyListState,
                flingBehavior = snapBehavior,
                contentPadding = PaddingValues(horizontal = paddingBetweenCards)
            ) {
                items(heroes, key = CharacterUI::hashCode) { characterUI: CharacterUI ->
                    HeroCard(
                        characterUI = characterUI,
                        onClick = {
                            val currentTime = System.currentTimeMillis()
                            if (currentTime - lastClickTime > TIME_TO_SECOND_CLICK) {
                                lastClickTime = currentTime
                                navController.navigate("details/${characterUI.id}")
                            }
                        })
                }
            }
        }
    }
}