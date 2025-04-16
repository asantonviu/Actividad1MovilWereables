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
import java.util.Locale
import javax.inject.Inject

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

    private val _weatherForecast: MutableState<List<WeatherModel>> = mutableStateOf(emptyList())
    var weatherForecast: State<List<WeatherModel>> = _weatherForecast

    // Localidad asocida a geolocalizacion
    private val _localidad: MutableState<String> = mutableStateOf("No Calculada")
    var localidad: State<String> = _localidad

    //Resumen Mtereologico
    private val _resumenMeteo: MutableState<String> = mutableStateOf("No Existe")
    var resumenMeteo: State<String> = _resumenMeteo


    suspend fun establecerUbicacion(latLng: LatLng) {
        Log.d("WeatherAppViewModel","establecerUbicacion Latitud:"+latLng.latitude+" Longitud:"+latLng.longitude)
        _ubicacion.value = latLng
        loadWeather(latLng)
        loadWeatherSummary(latLng)
        _localidad.value=loadLocalidad(latLng)?: "No Calculada"
    }
    suspend fun loadWeather(latLng: LatLng){
        val res=repository.fetchCurrentWeatherAndForecast(latLng.latitude,latLng.longitude)
        when (res) {
            is APIResult.Error -> _eventFlow.emit(UiEvent.ShowMessage("Error Consultando API"))
            is APIResult.Loading -> _eventFlow.emit(UiEvent.ShowMessage("Consultando API...."))
            is APIResult.Success -> {
                _weather.value = res.data?.toWeatherDTO() ?: EMPTYWeather
                _weatherForecast.value = res.data?.toWeatherDTOList() ?: emptyList<WeatherModel>()
            }

        }
    }

    suspend fun loadWeatherSummary(latLng: LatLng){
        val res=repository.fetchCurrentWeatherOverview(latLng.latitude,latLng.longitude)
        when (res) {
            is APIResult.Error -> _eventFlow.emit(UiEvent.ShowMessage("Error Consultando API"))
            is APIResult.Loading -> _eventFlow.emit(UiEvent.ShowMessage("Consultando API...."))
            is APIResult.Success -> _resumenMeteo.value= res.data?.toWeatherSumary() ?:"No Calculado"
        }
    }

    fun loadPrevision(latLng: LatLng): List<WeatherModel>{
        //return getPrevision(latLng)
        return emptyList()
    }

    fun loadLocalidad(latLng: LatLng) :String? {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
        val localidad = addresses?.firstOrNull()?.locality
        Log.d("WeatherAppViewModel", "loadLocalidad: $localidad")
        return localidad
    }
}