package es.viu.moviles.actividad1.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import es.viu.moviles.actividad1.ui.theme.weatherSumaryTipografy

/*
Clase que representa un componente Resumen Metereologica
El resumen es un texto que describe la metereologia
 */

@Composable
fun WeatherSummary(weatherSumary: String, modifier: Modifier = Modifier) {
    LazyColumn (
                modifier = modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
                ) {
                item {
                    Text(
                        text = weatherSumary,
                        textAlign = TextAlign.Justify,
                        style = weatherSumaryTipografy
                    )
                }

    }
}