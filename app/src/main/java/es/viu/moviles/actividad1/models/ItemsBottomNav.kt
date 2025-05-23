package es.viu.moviles.actividad1.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Surfing
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector
import es.viu.moviles.actividad1.ui.navigation.NavScreen

/*
Representa los botones de la barra de navegación inferior
 */
sealed class ItemsBottomNav (
    val icon:ImageVector,
    val titulo:String,
    val ruta:String) {

    object ItemBottomNav1:ItemsBottomNav(
        icon=Icons.Outlined.WbSunny,
        titulo= "Actual",
        ruta= NavScreen.HomeScreen.name
    )

    object ItemBottomNav2:ItemsBottomNav(
        icon=Icons.Outlined.Surfing,
        titulo= "Prevision",
        ruta= NavScreen.Prevision7DiasScreen.name
    )

    object ItemBottomNav3:ItemsBottomNav(
        icon=Icons.Outlined.History,
        titulo= "Historico",
        ruta= NavScreen.HistoricoScreen.name
    )
}