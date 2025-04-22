package es.viu.moviles.actividad1.models

/*
Representa el DTO a mostrar
Es lo que reciben las Screen que muestran datos metereologicos
 */



data class WeatherModel(
    val temperatura:String,
    val sensacionTermica:String,
    val presionAtmosferica:String,
    val humedad:String,
    val presenciaNubes:String,
    val lluvia:String,
    val fecha:String,
    val icono:String
)

val EMPTYWeather= WeatherModel("0","0","0","0","0","0","0","https://cdn.worldweatheronline.com/images/wsymbols01_png_64/wsymbol_0002_sunny_intervals.png",)

