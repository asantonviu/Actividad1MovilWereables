package es.viu.moviles.actividad1.data

import es.viu.moviles.actividad1.Constants
import es.viu.moviles.actividad1.service.APIResult
import es.viu.moviles.actividad1.service.WeatherAPIService
import es.viu.moviles.actividad1.service.WeatherHistoricalResponse
import es.viu.moviles.actividad1.service.WeatherOverviewResponse
import es.viu.moviles.actividad1.service.WeatherResponse
import javax.inject.Inject

/*
Repositorio que interactua con la API Metereologica
 */
class WeatherRepository @Inject constructor (private val apiService: WeatherAPIService) {
    /*
    Obtiene, para una latitud y longitud concreta, la metereologia actual y una prevision a 7 dias
    */
    suspend fun fetchCurrentWeatherAndForecast(
        lat: Double,
        lon: Double
    ): APIResult<WeatherResponse> {
        val response = try {
            apiService.getCurrentWeatherAndForecast(lat, lon, apiKey = Constants.API_KEY)
        } catch (e: Exception) {
            return APIResult.Error(e.message ?: "Unknown error")
        }
        return APIResult.Success(response)
    }

    /*
Obtiene el resumen metereologico actual  para una latitud y longitud concreta
 */
    suspend fun fetchCurrentWeatherOverview(
        lat: Double,
        lon: Double
    ): APIResult<WeatherOverviewResponse> {
        val response = try {
            apiService.getCurrentWeatherOverview(lat, lon, apiKey = Constants.API_KEY)
        } catch (e: Exception) {
            return APIResult.Error(e.message ?: "Unknown error")
        }
        return APIResult.Success(response)
    }

    /*
    Obtiene la metereologia en una fecha pasada para una latitud y longitud concreta
     */
    suspend fun fetchHistoricalWeather(
        lat: Double,
        lon: Double,
        dt: Long
    ): APIResult<WeatherHistoricalResponse> {
        val response = try {
            apiService.getHistoricalWeather(lat, lon,dt=dt, apiKey = Constants.API_KEY)
        } catch (e: Exception) {
            return APIResult.Error(e.message ?: "Unknown error")
        }
        return APIResult.Success(response)
    }
}