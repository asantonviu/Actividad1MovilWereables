package es.viu.moviles.actividad1.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.viu.moviles.actividad1.R

@ExperimentalMaterial3Api
@Composable
fun DateForecastScreen(onFetchDataForDate: (String) -> List<Pair<String, String>>) {
    var selectedDate by remember { mutableStateOf("") }
    var forecastData by remember { mutableStateOf<List<Pair<String, String>>>(emptyList()) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(stringResource(R.string.date_forecast_title)) })
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = { selectedDate = it },
                    label = { Text(stringResource(R.string.enter_date_hint)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(onClick = {
                    // Aquí deberías tener la lógica para validar la fecha y llamar a la función
                    // que obtiene los datos del pronóstico para esa fecha.
                    // Por ahora, simulamos la obtención de datos.
                    forecastData = onFetchDataForDate(selectedDate)
                }) {
                    Text(stringResource(R.string.fetch_forecast_button))
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (forecastData.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(forecastData) { (dayOfWeek, previousComponentInfo) ->
                            Text(text = "$dayOfWeek - Info: $previousComponentInfo")
                        }
                    }
                } else if (selectedDate.isNotEmpty()) {
                    Text(stringResource(R.string.no_forecast_data))
                }
            }
        }
    )
}