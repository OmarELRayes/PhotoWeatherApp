package com.example.photoweather.domain.model

data class WeatherData(
    val description: String,
    val temperature: Double,
    val humidity: Int,
    val country: String,
    val name: String
)
