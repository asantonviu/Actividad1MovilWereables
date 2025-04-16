package es.viu.moviles.actividad1.models




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

/*val datosMetereologicos:List<WeatherModel> =listOf(
    WeatherModel("12","25%","https://cdn.worldweatheronline.com/images/wsymbols01_png_64/wsymbol_0002_sunny_intervals.png","Guadarrama","Madrid","España"),
    WeatherModel("10","45%","https://cdn.worldweatheronline.com/images/wsymbols01_png_64/wsymbol_0002_sunny_intervals.png","El Escorial","Madrid","España")
    )

 */

/*fun getWeather(latLng: LatLng):WeatherModel{
    Log.d("WeatherModel","getWeather Latitud:"+latLng.latitude+" Longitud:"+latLng.longitude)
    return datosMetereologicos[0]
}*/

/*fun getPrevision(latLng: LatLng):List<WeatherModel>{
    Log.d("WeatherModel","getPrevision Latitud:"+latLng.latitude+" Longitud:"+latLng.longitude)
    return datosMetereologicos
}*/
