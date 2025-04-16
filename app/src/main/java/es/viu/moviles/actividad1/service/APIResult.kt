package es.viu.moviles.actividad1.service

sealed class APIResult<T> (val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : APIResult<T>(data)
    class Error<T>(message: String, data: T? = null) : APIResult<T>(data, message)
    class Loading<T> (data: T? = null) : APIResult<T>(data)
}