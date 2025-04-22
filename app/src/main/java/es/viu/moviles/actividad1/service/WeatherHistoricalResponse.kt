package es.viu.moviles.actividad1.service

import kotlinx.serialization.*


/*
Identifica la respuesta dada por el Servicio ante una petición de Metereologia con fecha pasada
 */

@Serializable
data class WeatherHistoricalResponse (
    val lat: Double,
    val lon: Double,
    val timezone: String,

    @SerialName("timezone_offset")
    val timezoneOffset: Long,

    val data: List<Datum>
)

@Serializable
data class Datum (
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Double?=null,

    @SerialName("feels_like")
    val feelsLike: Double?=null,

    val pressure: Long?=null,
    val humidity: Long?=null,

    @SerialName("dew_point")
    val dewPoint: Double,

    val clouds: Long?=null,
    val visibility: Long?=null,

    @SerialName("wind_speed")
    val windSpeed: Double,

    @SerialName("wind_deg")
    val windDeg: Long,

    val weather: List<Weather>,
    val rain: Rain?=null
)

@Serializable
data class HistoricalRain (
    @SerialName("1h")
    val the1H: Double
)

@Serializable
data class HistoricalWeather (
    val id: Long,
    val main: String,
    val description: String,
    val icon: String?=null
)