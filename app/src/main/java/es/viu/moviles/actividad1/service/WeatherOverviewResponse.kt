package es.viu.moviles.actividad1.service

import kotlinx.serialization.*

@Serializable
data class WeatherOverviewResponse (
    val lat: Double,
    val lon: Double,
    val tz: String,
    val date: String,
    val units: String,

    @SerialName("weather_overview")
    val weatherOverview: String
)