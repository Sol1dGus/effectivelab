import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.marvelheroes.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HeroCard(
    modifier: Modifier = Modifier,
    hero: Hero,
    onClick: () -> Unit
){
    Card(
        modifier = modifier
            .fillMaxHeight()
            .width(300.dp)
            .padding(horizontal = 16.dp)
            .padding(vertical = 48.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.BottomStart
        )
        {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(hero.imageUrl)
                    .build(),
                placeholder = painterResource(R.drawable.loading),
                modifier = modifier
                    .fillMaxSize(),
                contentDescription = null,
                error = painterResource(R.drawable.error)
            )
            // Текст поверх изображения
            Text(
                text = hero.name,
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.Black.copy(alpha = 0.5f))
            )
        }
    }
}