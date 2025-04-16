package es.viu.moviles.actividad1.data

import es.viu.moviles.actividad1.Constants
import es.viu.moviles.actividad1.service.APIResult
import es.viu.moviles.actividad1.service.WeatherAPIService
import es.viu.moviles.actividad1.service.WeatherOverviewResponse
import es.viu.moviles.actividad1.service.WeatherResponse
import javax.inject.Inject


class WeatherRepository @Inject constructor (private val apiService: WeatherAPIService) {

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
}