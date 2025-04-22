package es.viu.moviles.actividad1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

/*
Clase Helper para saber la ruta de navegacion actual
 */
@Composable
fun rutaActual(navController: NavHostController):String?=
    navController.currentBackStackEntryAsState().value?.destination?.route
