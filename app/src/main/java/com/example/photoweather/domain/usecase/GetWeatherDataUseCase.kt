package com.example.photoweather.domain.usecase

import com.example.photoweather.domain.mapper.WeatherDataMapper
import com.example.photoweather.domain.repository.WeatherDataRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetWeatherDataUseCase @Inject constructor(
    private val weatherDataRepository: WeatherDataRepository,
    private val mapper: WeatherDataMapper
) {
    suspend operator fun invoke(coordinates: Pair<Double, Double>) =
        mapper.map(weatherDataRepository.getWeatherData(coordinates))
}
