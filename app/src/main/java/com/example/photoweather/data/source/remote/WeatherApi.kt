package com.example.photoweather.data.source.remote

import com.example.photoweather.data.model.WeatherDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String = "0937aa8e08d8480d6530e2b4b97c14cc"
    ): WeatherDataResponse
}
