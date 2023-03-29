package com.example.photoweather.domain.usecase

import com.example.photoweather.domain.repository.WeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetWeatherPhotosUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke() = weatherRepository.getWeatherPhotos()
}
