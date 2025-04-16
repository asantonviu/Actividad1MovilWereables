package es.viu.moviles.actividad1

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import es.viu.moviles.actividad1.components.NavegacionInferior
import es.viu.moviles.actividad1.ui.navigation.WeatherAppNavigation
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import es.viu.moviles.actividad1.models.WeatherAppViewModel
import es.viu.moviles.actividad1.ui.theme.WeatherBackgroundGradient
import kotlinx.coroutines.tasks.await


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        enableEdgeToEdge()
        setContent {
            val locationUser = remember { mutableStateOf<LatLng?>(null) }
            val locationChecked = remember { mutableStateOf(false) }
            val permisoConcedido = remember { mutableStateOf(false) }

            // ViewModel se vincula al scope de la actividad
            val weatherAppViewModel: WeatherAppViewModel by viewModels()

            val permissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                permisoConcedido.value = isGranted
                locationChecked.value = true
                if (!isGranted) {
                    Log.e("MainActivity", "El usuario denegó el permiso")
                } else {
                    Log.d("MainActivity", "El usuario otorgó el permiso")
                }
            }

            // Solicitud inicial del permiso
            LaunchedEffect(Unit) {
                val permiso = ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
                if (permiso == PackageManager.PERMISSION_GRANTED) {
                    permisoConcedido.value = true
                    locationChecked.value = true
                } else {
                    permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }

            // Obtención de la ubicación cuando ya tenemos el permiso
            LaunchedEffect(permisoConcedido.value) {
                if (permisoConcedido.value) {
                    try {
                        val location = fusedLocationProviderClient.lastLocation.await()
                        if (location != null) {
                            locationUser.value = LatLng(location.latitude, location.longitude)
                            weatherAppViewModel.establecerUbicacion(LatLng(location.latitude, location.longitude))
                            Log.d("MainActivity", "Ubicación obtenida: ${location.latitude}, ${location.longitude}")
                        } else {
                            Log.e("MainActivity", "No se pudo obtener la última ubicación conocida")
                        }
                    } catch (e: Exception) {
                        Log.e("MainActivity", "Error al obtener ubicación", e)
                    }
                }
            }

            Surface(modifier = Modifier.fillMaxSize(), color= Color.Transparent)
              {
                Box(
                  modifier = Modifier
                      .fillMaxSize()
                      .background(WeatherBackgroundGradient)
                ) {
                    when {
                        !locationChecked.value -> {
                            Log.d("MainActivity", "Esperando ubicación...")
                        }

                        locationUser.value != null -> {
                            Log.d("MainActivity", "Navegando con ubicacion")
                            MainScreen()
                        }

                        else -> {
                            Log.d("MainActivity", "Navegando sin ubicacion")
                            MainScreen()
                        }
                    }
                }

              }
        }
    }



    @ExperimentalMaterial3Api
    @Composable
    fun MainScreen(){
         val weatherAppViewModel: WeatherAppViewModel = hiltViewModel()
         Log.d("MainScreen", "Inicio Lat:${weatherAppViewModel.ubicacion.value?.latitude} Long:${weatherAppViewModel.ubicacion.value?.longitude}")
         val navController= rememberNavController()
         Scaffold(
             bottomBar ={
             NavegacionInferior(navController)
                },
             containerColor = Color.Transparent
         )
         {padding ->
             Box(modifier = Modifier.padding(padding).fillMaxSize()){
                    WeatherAppNavigation(navController=navController,weatherAppViewModel)
             }
         }
    }


}