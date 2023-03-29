package com.example.photoweather.domain.usecase

import com.example.photoweather.domain.model.WeatherData
import com.example.photoweather.domain.model.WeatherPhoto
import com.example.photoweather.domain.repository.WeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class AddWeatherPhotoUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        weatherPhoto: WeatherData,
        image: String
    ) =
        weatherRepository.addWeatherPhoto(
            WeatherPhoto(
                image = image,
                description = weatherPhoto.description,
                temperature = weatherPhoto.temperature,
                humidity = weatherPhoto.humidity,
                country = weatherPhoto.country,
                name = weatherPhoto.name
            )
        )
}
