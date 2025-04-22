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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import es.viu.moviles.actividad1.models.WeatherAppViewModel
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import es.viu.moviles.actividad1.components.WeatherSummary
import es.viu.moviles.actividad1.ui.theme.topAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch

/*
Representa una Pantalla de Inicio
Muestra, para la ubicacion actual, la prevision actual y un resumen del mismo
Permite seleccionar otra localidad
 */
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(weatherAppViewModel: WeatherAppViewModel) {
    var mostrarInput by remember { mutableStateOf(false) }
    var textoLocalidad by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

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
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Boton para hacer una Seleccion manual
                // Botón para mostrar el input de ciudad manual
                Button(onClick = { mostrarInput = !mostrarInput }) {
                    Text(if (mostrarInput) "Cancelar" else "Cambiar ciudad")
                }

                // Input de localidad manual
                if (mostrarInput) {
                    OutlinedTextField(
                        value = textoLocalidad,
                        onValueChange = {
                            textoLocalidad = it
                            error = null
                        },
                        label = { Text("Introduce una ciudad") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = error != null
                    )
                    val scope = rememberCoroutineScope()
                    Button(onClick = {
                        scope.launch {
                            try {
                                val latLng = weatherAppViewModel.loadLocalidad(textoLocalidad)
                                weatherAppViewModel.establecerUbicacion(latLng)
                                mostrarInput = false
                            } catch (e: Exception) {
                                error = "Error al obtener la ubicación"
                                Log.e("HomeScreen", "Geocoder error", e)
                            }
                        }
                    }) {
                        Text("Buscar ubicación")
                    }

                    if (error != null) {
                        Text(text = error ?: "", color = Color.Red)
                    }
                }




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
