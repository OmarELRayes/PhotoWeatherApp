package com.example.photoweather.data.source.remote

import com.example.photoweather.data.model.WeatherDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        // This is only for task purposes, api key should saved in a more secure way (eg. gradle properties or system environments)
        @Query("appid") appId: String = "7087059a9570a83de3512a9ecde2e9b5"
    ): WeatherDataResponse
}
