package es.viu.moviles.actividad1.ui.screen


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.viu.moviles.actividad1.R
import es.viu.moviles.actividad1.components.WeatherCardRow
import es.viu.moviles.actividad1.components.WeatherScreenTitle
import es.viu.moviles.actividad1.models.WeatherAppViewModel
import es.viu.moviles.actividad1.ui.theme.topAppBar
/*
Representa una la Pantalla de prevision Metereologica
Muestra la prevision metereologica para la ubicación actual
 */
@ExperimentalMaterial3Api
@Composable
fun ForecastScreen(weatherAppViewModel: WeatherAppViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.daily_forecast_title),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        style = topAppBar
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent // Para usar el fonde de la MainActivity
                )
            )
        },
        containerColor = Color.Transparent,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // WeatherScreenTitle con un borde y padding alrededor
                weatherAppViewModel.ubicacion.value?.let {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp) // Añadimos padding para darle espacio
                            .border(2.dp, Color.Gray, shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)) // Borde con esquinas redondeadas
                    ) {
                        WeatherScreenTitle(localidad = weatherAppViewModel.localidad.value, latLng = it, modifier = Modifier)
                    }
                }
                LazyColumn(modifier = Modifier) {
                    items(weatherAppViewModel.weatherForecast.value){weather ->
                        WeatherCardRow(weather,cardImageSize=100.dp,modifier=Modifier)
                        Spacer(modifier = Modifier.height(1.dp))
                    }
                }
            }
        }
    )
}