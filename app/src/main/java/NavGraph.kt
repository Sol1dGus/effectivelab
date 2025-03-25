import androidx.compose.runtime.Composable
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

        composable(route = "details/{heroId}")
        { backStackEntry ->
            val heroId = backStackEntry.arguments?.getString("heroId")?.toIntOrNull();
            println(backStackEntry.arguments)
            println("heroId = $heroId")
            DetailsScreen(navController, heroId)
        }
    }
}