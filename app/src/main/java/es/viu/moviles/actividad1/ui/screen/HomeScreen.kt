package es.viu.moviles.actividad1.ui.screen

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.viu.moviles.actividad1.R
import es.viu.moviles.actividad1.components.WeatherCardRow
import es.viu.moviles.actividad1.components.WeatherScreenTitle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import es.viu.moviles.actividad1.models.WeatherAppViewModel
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.geometry.Offset
import es.viu.moviles.actividad1.components.WeatherSummary
import es.viu.moviles.actividad1.ui.theme.topAppBar


@ExperimentalMaterial3Api
@Composable
fun HomeScreen(weatherAppViewModel: WeatherAppViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.home_screen_title),
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

                WeatherCardRow(weatherModel=weatherAppViewModel.weather.value,modifier=Modifier)
                WeatherSummary(weatherSumary =weatherAppViewModel.resumenMeteo.value,modifier=Modifier)
            }
        }
    )
}
