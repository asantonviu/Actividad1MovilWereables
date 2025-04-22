package es.viu.moviles.actividad1.service

import kotlinx.serialization.*
/*
Identifica la respuesta dada por el Servicio ante una petición de Resumen Metereológico
 */
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