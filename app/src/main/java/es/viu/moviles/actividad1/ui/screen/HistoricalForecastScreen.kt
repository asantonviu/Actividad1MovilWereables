package es.viu.moviles.actividad1.ui.screen



import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock



@ExperimentalMaterial3Api
@Composable
fun HistoricalForecastScreen(weatherAppViewModel: WeatherAppViewModel) {
    var mostrarDatePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Clock.System.now().toEpochMilliseconds(),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                // Solo se puede seleccionar una fecha si es igual o menor a la fecha actual
                return utcTimeMillis <= Clock.System.now().toEpochMilliseconds()
            }
        }
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.historyc_forecast_title),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        style = topAppBar
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
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
                val scope = rememberCoroutineScope()
                // Botón para mostrar el selector de fecha
                Button(onClick = { mostrarDatePicker = true }) {
                    Text(stringResource(R.string.selec_fecha))
                }
                if (mostrarDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = { mostrarDatePicker = false },
                        confirmButton = {
                            TextButton(onClick = {
                                scope.launch {
                                    weatherAppViewModel.establecerFechaHistorico(datePickerState.selectedDateMillis!! / 1000)
                                }
                                mostrarDatePicker = false
                            }) {
                                Text("Aceptar")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { mostrarDatePicker = false }) {
                                Text("Cancelar")
                            }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }

                // Sección igual que en HomeScreen
                weatherAppViewModel.ubicacion.value?.let {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .border(
                                2.dp,
                                Color.Gray,
                                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                            )
                    ) {
                        WeatherScreenTitle(
                            localidad = weatherAppViewModel.localidad.value,
                            latLng = it,
                            modifier = Modifier
                        )
                    }
                }

                WeatherCardRow(
                    weatherModel = weatherAppViewModel.historicalweather.value,
                    modifier = Modifier
                )
            }
        }
    )
}