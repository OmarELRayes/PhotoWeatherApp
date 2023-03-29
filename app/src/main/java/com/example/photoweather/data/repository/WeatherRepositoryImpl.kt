package com.example.photoweather.data.repository

import com.example.photoweather.data.model.WeatherDataResponse
import com.example.photoweather.data.source.local.WeatherLocalDataSource
import com.example.photoweather.data.source.remote.WeatherRemoteDataSource
import com.example.photoweather.domain.model.WeatherPhoto
import com.example.photoweather.domain.repository.WeatherRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val weatherLocalDataSource: WeatherLocalDataSource
) : WeatherRepository {
    override suspend fun getWeatherData(coordinates: Pair<Double, Double>): WeatherDataResponse {
        return weatherRemoteDataSource.getWeatherData(coordinates)
    }

    override suspend fun addWeatherPhoto(item: WeatherPhoto) {
        weatherLocalDataSource.addWeatherPhoto(item)
    }

    override suspend fun getWeatherPhotos(): Flow<List<WeatherPhoto>> {
        return weatherLocalDataSource.getWeatherPhotos()
    }
}
