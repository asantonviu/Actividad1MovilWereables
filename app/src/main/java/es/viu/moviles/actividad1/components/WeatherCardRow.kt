package es.viu.moviles.actividad1.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.viu.moviles.actividad1.models.WeatherModel

/*
Clase que representa una fila Metereologica
 */
@Composable
fun WeatherCardRow(weatherModel: WeatherModel, cardImageSize:Dp=150.dp,modifier: Modifier = Modifier) {
    Column (modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top)
    {
            WeatherCardImage(weatherModel,cardImageSize,modifier)
            Spacer(modifier = Modifier.height(2.dp))
            WeatherCard(weatherModel,modifier)
    }
}