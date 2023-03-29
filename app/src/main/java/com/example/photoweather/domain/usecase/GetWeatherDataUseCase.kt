package com.example.photoweather.domain.usecase

import com.example.photoweather.domain.mapper.WeatherDataMapper
import com.example.photoweather.domain.repository.WeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetWeatherDataUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val mapper: WeatherDataMapper
) {
    suspend operator fun invoke(coordinates: Pair<Double, Double>) =
        mapper.map(weatherRepository.getWeatherData(coordinates))
}
