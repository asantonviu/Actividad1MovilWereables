package es.viu.moviles.actividad1.models


import android.util.Log
import es.viu.moviles.actividad1.service.WeatherHistoricalResponse
import es.viu.moviles.actividad1.service.WeatherOverviewResponse
import es.viu.moviles.actividad1.service.WeatherResponse
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/*
Funciones de extension sobre las respuestas de la API para obtener los DTO,complejos o primitivos
 */

//Funcion de extension para mapear respuesta de API a Modelo Interno (DTO)
fun WeatherResponse.toWeatherDTO(): WeatherModel {
    return WeatherModel(
        temperatura = "${current.temp}°C",
        humedad = "${current.humidity}%",
        sensacionTermica = "${current.feelsLike}°C",
        presenciaNubes = "${current.clouds}%",
        presionAtmosferica="${current.pressure}hPa",
        lluvia = "${current.rain?.the1H ?: 0} mm/h",
        fecha = obtenFechaFromDT(current.dt),
        icono = "https://openweathermap.org/img/wn/${current.weather.firstOrNull()?.icon ?: "01d"}@2x.png"
    )
}

private fun obtenFechaFromDT(utcMillis:Long):String{
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        .withZone(ZoneId.systemDefault())
    return formatter.format(Instant.ofEpochSecond(utcMillis))
}

fun WeatherResponse.toWeatherDTOList():List<WeatherModel>{
    return daily.map { weather ->
        WeatherModel(
            temperatura = "${weather.temp.day}°C",
            humedad = "${weather.humidity}%",
            sensacionTermica = "${weather.feelsLike.day}°C",
            presenciaNubes = "${weather.clouds}%",
            presionAtmosferica="${weather.pressure}hPa",
            lluvia = "${weather.rain ?: 0} mm/h",
            fecha = obtenFechaFromDT(weather.dt),
            icono = "https://openweathermap.org/img/wn/${weather.weather.firstOrNull()?.icon ?: "01d"}@2x.png"
        )
    }
}


fun WeatherHistoricalResponse.toHistoricalWeather(dt:Long): WeatherModel {
    Log.d("WeatherHistoricalResponse","WeatherHistoricalResponse.toHistoricalWeather dt:"+dt+" Fecha:"+obtenFechaFromDT(dt))
    return WeatherModel(
        temperatura = "${data[0].temp}°C",
        humedad = "${data[0].humidity}%",
        sensacionTermica = "${data[0].feelsLike}°C",
        presenciaNubes = "${data[0].clouds}%",
        presionAtmosferica="${data[0].pressure}hPa",
        lluvia = "${data[0].rain?.the1H ?: 0} mm/h",
        fecha = obtenFechaFromDT(dt),
        icono = "https://openweathermap.org/img/wn/${data[0].weather.firstOrNull()?.icon ?: "01d"}@2x.png"
    )
}

//Funcion de extension para mapear respuesta de API (Resumen) a String
fun WeatherOverviewResponse.toWeatherSumary(): String {
    return weatherOverview
}