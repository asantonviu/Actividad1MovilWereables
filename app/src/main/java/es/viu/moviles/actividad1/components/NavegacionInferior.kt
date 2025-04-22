package es.viu.moviles.actividad1.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import es.viu.moviles.actividad1.models.ItemsBottomNav.*
import androidx.compose.material3.Text
import es.viu.moviles.actividad1.ui.navigation.rutaActual

/*
Clase que representa la barra de navegación inferior que contendrá 3 botones de navegación
 1) Metereologia Actual. Valor seleccionado por defecto
 2) Previsión a 7 dias
 3) Historico. Por defecto el dia de ayer
 */
@Composable
fun NavegacionInferior(
    navController:NavHostController
){
    val menu_items= listOf(
          ItemBottomNav1,
          ItemBottomNav2,
          ItemBottomNav3
    )
    BottomAppBar {
        NavigationBar(
            containerColor= MaterialTheme.colorScheme.inverseOnSurface
        ) {
            menu_items.forEach { item ->
                val idSeleccionado= rutaActual(navController) == item.ruta
                NavigationBarItem(
                    selected = idSeleccionado,
                    onClick = {navController.navigate(item.ruta)},
                    icon= { Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.titulo)
                        },
                    label = {
                        Text(text=item.titulo)
                    }
                )
            }
        }
    }
}