package es.viu.moviles.actividad1.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import java.time.LocalTime

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Orange2Dark = Color(0xFF4E342E)
val OrangeDark = Color(0xFFEF6C00)  // Naranja Oscuro
val BlueLight = Color(0xFFB3E5FC)   // Azul celeste claro
val BlueMid   = Color(0xFF81D4FA)   // Azul celeste medio
val BlueDark  = Color(0xFF4FC3F7)   // Azul celeste oscuro

// Tonos adicionales para momentos del día
val DawnLight = Color(0xFFE1F5FE)       // Amanecer claro (azulado)
val SunsetDark = Color(0xFF1565C0)      // Azul oscuro para atardecer
val NightStart = Color(0xFF0D47A1)      // Noche azul profundo
val NightEnd = Color(0xFF001F54)        // Noche total (casi negro azulado)

// Esta propiedad se calcula dinámicamente según la hora
val WeatherBackgroundGradient: Brush
    get() {
        val hour = LocalTime.now().hour
        return when (hour) {
            in 6..11 -> Brush.linearGradient(listOf(DawnLight, BlueLight))
            in 12..17 -> Brush.linearGradient(listOf(BlueLight, BlueDark))
            in 18..21 -> Brush.linearGradient(listOf(BlueMid, SunsetDark))
            else -> Brush.linearGradient(listOf(NightStart, NightEnd))
        }
    }