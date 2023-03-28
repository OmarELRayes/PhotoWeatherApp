package com.example.photoweather.domain.mapper

import com.example.photoweather.data.model.WeatherDataResponse
import com.example.photoweather.domain.model.WeatherData
import javax.inject.Inject

class WeatherDataMapper @Inject constructor() : IMapper<WeatherDataResponse, WeatherData> {
    override fun map(inputFormat: WeatherDataResponse) = WeatherData(
        description = inputFormat.weather.firstOrNull()?.description ?: "",
        temperature = inputFormat.main.temp,
        humidity = inputFormat.main.humidity,
        country = inputFormat.sys.country,
        name = inputFormat.name
    )

    override fun reverseMap(inputFormat: WeatherData): WeatherDataResponse {
        TODO("Not yet implemented")
    }
}
