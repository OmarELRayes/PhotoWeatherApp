package com.example.photoweather.domain.repository

import com.example.photoweather.data.model.WeatherDataResponse

interface WeatherDataRepository {
    suspend fun getWeatherData(coordinates: Pair<Double, Double>): WeatherDataResponse
}
