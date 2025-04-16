package es.viu.moviles.actividad1.models

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import es.viu.moviles.actividad1.data.WeatherRepository
import es.viu.moviles.actividad1.service.APIResult
import es.viu.moviles.actividad1.ui.screen.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import java.time.Instant
import java.util.Locale
import javax.inject.Inject

/*
MVVM utilizado por la aplicacion
 */

@HiltViewModel
class WeatherAppViewModel @Inject constructor(
    private val repository: WeatherRepository,
    @ApplicationContext private val context: Context
):ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    // Ubicacion Actual
    private val _ubicacion: MutableState<LatLng?> = mutableStateOf(LatLng(0.toDouble(),0.toDouble()))
    val ubicacion: State<LatLng?> = _ubicacion

    //Prevision Metereologica Actual
    private val _weather:MutableState<WeatherModel> = mutableStateOf(EMPTYWeather)
    var weather: State<WeatherModel> = _weather

    // Prevision Metereologica
    private val _weatherForecast: MutableState<List<WeatherModel>> = mutableStateOf(emptyList())
    var weatherForecast: State<List<WeatherModel>> = _weatherForecast

    // Datos Historicos
    private val _historicalweather: MutableState<WeatherModel> = mutableStateOf(EMPTYWeather)
    var historicalweather: State<WeatherModel> = _historicalweather


    // Localidad asocida a geolocalizacion
    private val _localidad: MutableState<String> = mutableStateOf("No Calculada. Seleccione Manualmente")
    var localidad: State<String> = _localidad

    //Resumen Mtereologico
    private val _resumenMeteo: MutableState<String> = mutableStateOf("No Existe")
    var resumenMeteo: State<String> = _resumenMeteo

    // dt fecha consulta Historico
    private val _dt: MutableState<Long> = mutableStateOf(Instant.now().epochSecond)
    val dt: State<Long?> = _dt


    suspend fun establecerFechaHistorico(dt:Long=ayer()){
        Log.d("WeatherAppViewModel","establecerFechaHistorico dt:"+dt)
         _dt.value = dt
         loadHistoricalWeather(_ubicacion.value!!,dt)
    }
    suspend fun establecerUbicacion(latLng: LatLng) {
        Log.d("WeatherAppViewModel","establecerUbicacion Latitud:"+latLng.latitude+" Longitud:"+latLng.longitude)
        _ubicacion.value = latLng
        loadWeather(latLng)
        loadWeatherSummary(latLng)
        establecerFechaHistorico()// Establecemos hoy menos un dia
        _localidad.value=loadLocalidad(latLng)?: "No Calculada. Seleccione Manualmente"
    }
    suspend fun loadWeather(latLng: LatLng){
        val res=repository.fetchCurrentWeatherAndForecast(latLng.latitude,latLng.longitude)
        when (res) {
            is APIResult.Error -> _eventFlow.emit(UiEvent.ShowMessage("Error Consultando API Actual"))
            is APIResult.Loading -> _eventFlow.emit(UiEvent.ShowMessage("Consultando API Actual...."))
            is APIResult.Success -> {
                _weather.value = res.data?.toWeatherDTO() ?: EMPTYWeather
                _weatherForecast.value = res.data?.toWeatherDTOList() ?: emptyList<WeatherModel>()
            }

        }
    }

    suspend fun loadWeatherSummary(latLng: LatLng){
        val res=repository.fetchCurrentWeatherOverview(latLng.latitude,latLng.longitude)
        when (res) {
            is APIResult.Error -> _eventFlow.emit(UiEvent.ShowMessage("Error Consultando API Resumen"))
            is APIResult.Loading -> _eventFlow.emit(UiEvent.ShowMessage("Consultando API Resumen...."))
            is APIResult.Success -> _resumenMeteo.value= res.data?.toWeatherSumary() ?:"No Calculado"
        }
    }

    suspend fun loadHistoricalWeather(latLng: LatLng,dt:Long){
        Log.d("WeatherAppViewModel","loadHistoricalWeather dt:"+dt)
        val res=repository.fetchHistoricalWeather(latLng.latitude,latLng.longitude,dt=dt)
        when (res) {
            is APIResult.Error -> {
                Log.e("WeatherAppViewModel", "Error Consultando API Historica: ${res.message}")
                _eventFlow.emit(UiEvent.ShowMessage("Error Consultando API Historica"))
            }
            is APIResult.Loading -> _eventFlow.emit(UiEvent.ShowMessage("Consultando API Historica...."))
            is APIResult.Success -> {
                Log.d("WeatherAppViewModel", "Datos históricos obtenidos: ${res.data}")
                _historicalweather.value= res.data?.toHistoricalWeather(dt) ?: EMPTYWeather
            }
        }
    }

    // Utiliza Geocoder API para obtener la localidad a partir de la Lat/Lon
    fun loadLocalidad(latLng: LatLng) :String? {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
        val localidad = addresses?.firstOrNull()?.locality
        Log.d("WeatherAppViewModel", "loadLocalidad: $localidad")
        return localidad
    }

    // Utiliza Geocoder API para obtener la Lat/Lon a partir de una localidad
    fun loadLocalidad(nombre: String): LatLng {
        val geocoder = Geocoder(context, Locale.getDefault())
        val results = geocoder.getFromLocationName(nombre, 1)
        if (results.isNullOrEmpty()) {
            throw IllegalArgumentException("No se encontró la ciudad: $nombre")
        }
        val loc = results.first()
        return LatLng(loc.latitude, loc.longitude)
    }

    fun ayer(): Long {
        val ahora = Clock.System.now()
        val haceUnDia = ahora.minus(DateTimePeriod(days = 1), TimeZone.currentSystemDefault())
        return haceUnDia.epochSeconds
    }
}