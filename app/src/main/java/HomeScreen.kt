import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val listOfItems = listOf(
        Hero(1, "Deadpool", "Man with cancer", "https://s3-alpha-sig.figma.com/img/fe6f/5401/a1fcbba62871e9372ad6ba08bc49f429?Expires=1742774400&Key-Pair-Id=APKAQ4GOSFWCW27IBOMQ&Signature=a67OZq~0uCbee7J6Vs5xODCy~xfuPaEjCV5xy6jPwG80JTCnbT-dtVo2OVKgXON5Azzx2VrfKFi8bq308ssn22jh-3Docn1OXubsBvD02FnHqpdPM7-uCEhlqi5aV568pAmvVIlu5PrFtski38kelW8~okzxssTXf6YAOuI7XDf5-nUJXJKQwJUAaMSL-Qj6O6DEVKtrBx7ugYoFs3PMuOdiuDx4HJ3F6g-K4t0NrFoGS43m7t9pZ18NaN9YcR457iU69RrDXD1-UfrUmcZsSdb3jFvoweUPFrudGVLKG4WU8l017QaQVo7uNXoSBaeHgVuPWJaKgrrMlz744UwvFQ__"),
        Hero(2, "Iron Man", "Really strong man", "https://s3-alpha-sig.figma.com/img/d6ff/6e53/06e9a778c50e17ebd04b812b3a8258ef?Expires=1742774400&Key-Pair-Id=APKAQ4GOSFWCW27IBOMQ&Signature=uZnvvaWy7lEHHy6UJO~pxCbBRAqGD2~fkZF4XcLwmWFJ-56KTs-75tIZQ1-xmSBeeuiJHtupB91scDoLOrtxoOJYAln~ypEAqdQPSY~jM6wZz6ClhVAjVR2CI4LvE8XNPP6Oe0lOcI7QI~lcV9Nd27iX5rCSKMzUYd7ugpbmpuhd9WekpP56TesR-lmVBRp3-t3jHUjEZGt0tU04-MtFoyrfIwwscALuuNTPcEVczs1Pt85fZiK0cvfllMrSQYAQIThBDpS8~srvKCIpVmH~TTFCnN9Q7c6~0aLrW32wRdcMQd6spM2kViqvq1ULiO4pkoP69jsNj140vkbCrsTqKw__"),
        Hero(3, "Spider Man", "Like spider and man in one moment", "https://s3-alpha-sig.figma.com/img/3afa/826d/91d576800cc61a71d74ddcc6fbc7420d?Expires=1743379200&Key-Pair-Id=APKAQ4GOSFWCW27IBOMQ&Signature=BmsgEd6N~d5HQxGWpZFjI2ylU1mxkoChW8xSx~HyL0-1ura~FKFuqm4KqeTa9hLfxupjIctblLSMVkJTyK2L9XDSq7H0858ohmCFZmXNiJNST9riy8rdFkadqMf0eg6YbzqPHeNDE1V9ovisFzdU00j~h88yDyrAeqmxSQBYCbeqCVvLAmim8UFW1ifnhFWSLexwx3akgNT~6d2Trniu~g0V6hDymGRCsnQ455Jfg5qZvBMunRED6xODYK8~7iV2Wyhqb3~6ie7YqkrG0qwR7EJLL-KFgpfjxufXepesDssDM~PtJRA743lHP1HpHbhaGviBD9mW0poHzc6ymMmA0g__")
    )

    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Фоновое изображение
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Фоновое изображение",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Логотип и текст
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
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
                    .height(100.dp)
                    .padding(bottom = 16.dp),
                contentDescription = null,
                error = painterResource(R.drawable.error)
            )

            Text(
                text = "Choose your hero!",
                Modifier
                    .wrapContentWidth(),
                fontSize = 30.sp,
                color = Color.White
            )

            // Карты с героями
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                state = lazyListState,
                flingBehavior = snapBehavior
            ) {
                items(listOfItems, key = Hero::hashCode) { hero: Hero ->
                    HeroCard(
                        hero = hero,
                        onClick = {
                            navController.navigate("details/${hero.id}")
                        })
                }
            }
        }

    }
}