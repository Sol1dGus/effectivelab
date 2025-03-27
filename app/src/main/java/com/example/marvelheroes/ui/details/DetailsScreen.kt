package com.example.marvelheroes.ui.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.marvelheroes.R
import com.example.marvelheroes.data.models.Hero
import com.example.marvelheroes.data.repository.HeroRepository

@Composable
fun DetailsScreen(navController: NavController, heroId : Int?) {
    val heroRepository = remember { HeroRepository() }
    val heroes = heroRepository.getHeroList()
    val hero : Hero
    var lastClickTime by remember { mutableLongStateOf(0L) }
    if (heroId != null)
    {
        hero = heroes[heroId]
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

            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 36.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF000000)
                ),

                border = BorderStroke(1.dp, Color.Red),
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = hero.name,
                        color = Color.White,
                        fontSize = 36.sp,
                        modifier = Modifier.padding(8.dp)
                    )

                    Text(
                        text = hero.description,
                        color = Color.White,
                        fontSize = 16.sp,
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
                        if (currentTime - lastClickTime > 500) {
                            lastClickTime = currentTime
                            navController.popBackStack()
                        }
                    },
                    colors = ButtonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Black,
                        disabledContentColor = Color.Black
                    ),
                    border = BorderStroke(width = 1.dp, color = Color.Red)
                    ) {
                    Text(text = "<")
                }
            }
        }
    }
    else
    {
        Text(text = "com.example.marvelheroes.data.models.Hero wasn't found")
    }

}