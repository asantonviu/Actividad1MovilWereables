package es.viu.moviles.actividad1.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import es.viu.moviles.actividad1.R
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Dp
import es.viu.moviles.actividad1.models.WeatherModel

/*
Clase que representa un componente Imagen Metereologica
La imagen representa la metereologia
 */

@Composable
fun WeatherCardImage(weatherModel: WeatherModel, cardImageSize: Dp, modifier: Modifier = Modifier) {
    Box ( modifier = modifier,
        contentAlignment = Alignment.CenterStart
        ) {
        AsyncImage(
            model = weatherModel.icono,
            contentDescription = stringResource(R.string.weather_icon),
            modifier = modifier
                .size(cardImageSize)
                .padding(bottom = 8.dp)

        )
    }
}