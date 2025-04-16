package es.viu.moviles.actividad1.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.viu.moviles.actividad1.models.WeatherAppViewModel
import es.viu.moviles.actividad1.ui.screen.HomeScreen
import es.viu.moviles.actividad1.ui.screen.ForecastScreen
import es.viu.moviles.actividad1.ui.screen.HistoricalForecastScreen

@ExperimentalMaterial3Api
@Composable
fun WeatherAppNavigation(
    navController: NavHostController= rememberNavController(),
    viewModel: WeatherAppViewModel
){

       NavHost(navController = navController,
               startDestination = NavScreen.HomeScreen.name) {
           composable(NavScreen.HomeScreen.name) {
               HomeScreen(viewModel)
           }
           composable(NavScreen.Prevision7DiasScreen.name) {
               ForecastScreen(viewModel)
           }
           composable(NavScreen.HistoricoScreen.name) {
               HistoricalForecastScreen(viewModel)
           }
       }

}