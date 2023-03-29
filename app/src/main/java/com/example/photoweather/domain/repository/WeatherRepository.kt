package com.example.photoweather.domain.repository

import com.example.photoweather.data.model.WeatherDataResponse
import com.example.photoweather.domain.model.WeatherPhoto

interface WeatherRepository {
    suspend fun getWeatherData(coordinates: Pair<Double, Double>): WeatherDataResponse

    suspend fun addWeatherPhoto(item: WeatherPhoto)
}
