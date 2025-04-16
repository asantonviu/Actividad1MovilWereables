package es.viu.moviles.actividad1

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.viu.moviles.actividad1.Constants.Companion.BASE_URL
import es.viu.moviles.actividad1.service.WeatherAPIService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

/*
Permite la inicializacion de Retrofit para que Hilt pueda hacer injeccions
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }
    @Provides
    @Singleton
    fun provideWeatherAPIService(retrofit: Retrofit): WeatherAPIService =
        retrofit.create(WeatherAPIService::class.java)
}