package es.viu.moviles.actividad1.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import es.viu.moviles.actividad1.models.WeatherModel
import es.viu.moviles.actividad1.ui.theme.weatherCardTipografy


@Composable
fun WeatherCard(weatherModel: WeatherModel, modifier: Modifier=Modifier) {

   Column (modifier = modifier,
           horizontalAlignment =   Alignment.CenterHorizontally,
           verticalArrangement =   Arrangement.SpaceBetween
       ) {
                    Text(
                       text = weatherModel.fecha,
                       textAlign = TextAlign.Center,
                       style = weatherCardTipografy,
                    )
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                        Text(
                            text = "Temp: "+weatherModel.temperatura,
                            textAlign = TextAlign.Left,
                            style = weatherCardTipografy,
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Hum: "+weatherModel.humedad,
                            textAlign = TextAlign.Right,
                            style = weatherCardTipografy,
                        )
                    }
                    Spacer(modifier = Modifier.height(1.dp))
                    Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                       Text(
                           text = "Sens: "+weatherModel.sensacionTermica,
                           textAlign = TextAlign.Left,
                           style = weatherCardTipografy,
                       )
                       Spacer(modifier = Modifier.width(5.dp))
                       Text(
                           text = "Nubes: "+weatherModel.presenciaNubes,
                           textAlign = TextAlign.Right,
                           style = weatherCardTipografy,
                       )
                   }
                   Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                       Text(
                           text = "Pres: "+weatherModel.presionAtmosferica,
                           textAlign = TextAlign.Left,
                           style = weatherCardTipografy,
                       )
                       Spacer(modifier = Modifier.width(5.dp))
                       Text(
                           text = "Lluvia: "+weatherModel.lluvia,
                           textAlign = TextAlign.Right,
                           style = weatherCardTipografy,
                       )
                   }

   }

}