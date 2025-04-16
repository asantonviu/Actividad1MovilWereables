package es.viu.moviles.actividad1.service

import retrofit2.http.GET
import retrofit2.http.Query

/*
Servicio REST OpenWeatherMap
 */
interface WeatherAPIService {

    @GET("data/3.0/onecall")
    suspend fun getCurrentWeatherAndForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String = "es",
        @Query("units") units: String = "metric",
        @Query("exclude") exclude: String = "hourly,minutely",
        @Query("appid") apiKey: String
    ): WeatherResponse

    @GET("data/3.0/onecall/overview")
    suspend fun getCurrentWeatherOverview(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String = "es",
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String
    ): WeatherOverviewResponse
}
