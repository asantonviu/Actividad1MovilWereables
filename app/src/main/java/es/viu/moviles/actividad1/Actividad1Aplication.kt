package es.viu.moviles.actividad1

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // Para poder usar inyecciones de Hilt, en concreto el ViewModel
class Actividad1Aplication : Application()