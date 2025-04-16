package es.viu.moviles.actividad1.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import es.viu.moviles.actividad1.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

//Estilo para WeatherTitle
val titleFont = FontFamily(Font(R.font.lora_bold))
val weatherScreenTitleTipografy= TextStyle(
    color = OrangeDark,
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = titleFont
)


//Estilo para WeatherCard
val dataFont = FontFamily(Font(R.font.roboto_mono_regular))
val weatherCardTipografy= TextStyle(
    color = OrangeDark,
    fontSize = 15.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = dataFont,
    shadow = Shadow(
        color = Color.Black,
        offset = Offset(4f, 4f),
        blurRadius = 8f
    )
)


//Estilo para WeatherSumary
val sumaryFont = FontFamily(Font(R.font.lora_regular))
val weatherSumaryTipografy= TextStyle(
    color = Orange2Dark,
    fontSize = 17.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = sumaryFont,
    lineHeight = 22.sp,
)

val topAppBar = TextStyle(
    color = Color.White,
    fontSize = 25.sp,
    fontWeight = FontWeight.Bold,
    shadow = Shadow(
        color = Color.Gray,
        offset = Offset(4f, 4f),
        blurRadius = 8f
    )
)