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
import com.example.marvelheroes.data.models.Hero
import com.example.marvelheroes.ui.components.HeroCard
import com.example.marvelheroes.ui.loading.LoadingScreen
import com.example.marvelheroes.ui.error.ErrorScreen

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
            uiState.heroes
        )
    }
}

@Composable
fun SuccessScreen(navController: NavController, modifier: Modifier, heroes: List<Hero>) {

    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
    val heroes = heroes
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
                    .data("https://s3-alpha-sig.figma.com/img/2620/9b5f/c53e66e73cfdc31dca0326074f3a0bdd?Expires=1743379200&Key-Pair-Id=APKAQ4GOSFWCW27IBOMQ&Signature=F3g8MJ9uzTUhKZfupNQKbhDC-F2GNs97GtUIIJeNSsXDnBm2SYHYVmD~JZQ8kMx1Imr2w4Gzd19SXzISb47fdI2AU~Z7Of4i0ngxucH7xZSJTOumwcU9nYUFAI3uLBiT3Z4tA24d9OJLBEUlTZw6fSuMsSuNgPAIlI2j94O0TioN5F4cOtnu0Lo5X7-4VnatFO36usYMmbuGV8o~4IeZjZjds0GUeACzOL5DwqHnBPOnjQs84~RmEhggHZp2RfbK1yokymVNvSzwzC~Ni01dL6B9mU3TvBj-VREEijiV5lwR61jvD~f48C69U0a2QsPUuRb6nO27sorKoZrg4NMLCQ__")
                    .build(),
                placeholder = painterResource(R.drawable.loading),
                modifier = modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(top = 16.dp),
                contentDescription = null,
                error = painterResource(R.drawable.error)
            )

            Text(
                text = "Choose your hero!",
                modifier
                    .wrapContentWidth()
                    .padding(top = 24.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.White
            )

            LazyRow(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 12.dp),

                state = lazyListState,
                flingBehavior = snapBehavior,
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(heroes, key = Hero::hashCode) { hero: Hero ->
                    HeroCard(
                        hero = hero,
                        onClick = {
                            val currentTime = System.currentTimeMillis()
                            if (currentTime - lastClickTime > TIME_TO_SECOND_CLICK) {
                                lastClickTime = currentTime
                                navController.navigate("details/${hero.id}")
                            }
                        })
                }
            }
        }
    }
}