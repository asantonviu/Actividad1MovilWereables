package es.viu.moviles.actividad1.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import es.viu.moviles.actividad1.ui.theme.weatherScreenTitleTipografy



@Composable
fun WeatherScreenTitle(localidad:String,latLng: LatLng, modifier: Modifier = Modifier) {
    Row (modifier = modifier,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Column(modifier = modifier){
            Text(
                text = localidad,
                style = weatherScreenTitleTipografy,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
            Spacer(modifier = modifier.height(2.dp))
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = latLng.latitude.toString(),
                    style = weatherScreenTitleTipografy,
                    textAlign = TextAlign.Left
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = latLng.longitude.toString(),
                    style = weatherScreenTitleTipografy,
                    textAlign = TextAlign.Right
                )
            }
        }
    }
}