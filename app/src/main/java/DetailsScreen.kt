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
import androidx.compose.runtime.remember
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

@Composable
fun DetailsScreen(navController: NavController, heroId : Int?) {
    val heroList = remember {HeroList()}
    val heroes = heroList.getHeroList()
    val hero : Hero;
    if (heroId != null)
    {
        hero = heroes.get(heroId)
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
                Column {
                    Text(
                        text = hero.name,
                        color = Color.White,
                        fontSize = 36.sp
                    )

                    Text(
                        text = hero.description,
                        color = Color.White,
                        fontSize = 16.sp
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
                    onClick = { navController.popBackStack() },
                    colors = ButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.White,
                        disabledContainerColor = Color.White,
                        disabledContentColor = Color.White
                    )


                    ) {
                    Text(text = "Back")
                }
            }
        }
    }
    else
    {
        Text(text = "Hero wasn't found")
    }

}