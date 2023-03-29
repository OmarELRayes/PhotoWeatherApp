package com.example.photoweather.data.source.local

import com.example.photoweather.domain.model.WeatherPhoto
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class WeatherLocalDataSource @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val dao: WeatherPhotoDao
) {
    suspend fun addWeatherPhoto(weatherPhoto: WeatherPhoto) = with(dispatcher) {
        return@with dao.addWeatherPhoto(weatherPhoto)
    }

    suspend fun getWeatherPhotos() = with(dispatcher) {
        return@with dao.getWeatherPhotos()
    }
}
