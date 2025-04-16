package es.viu.moviles.actividad1.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.viu.moviles.actividad1.models.WeatherModel


@Composable
fun WeatherCardRow(weatherModel: WeatherModel, cardImageSize:Dp=150.dp,modifier: Modifier = Modifier) {
    Column (modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
            WeatherCardImage(weatherModel,cardImageSize,modifier)
            Spacer(modifier = Modifier.height(2.dp))
            WeatherCard(weatherModel,modifier)
    }
}