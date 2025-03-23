import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Nav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home")
    {
        composable(route = "home")
        {
            HomeScreen(navController)
        }

        composable(route = "detail")
        {
            DetailsScreen(navController)
        }
    }
}