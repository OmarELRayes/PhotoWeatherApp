package com.example.photoweather.data.repository

import com.example.photoweather.data.model.WeatherDataResponse
import com.example.photoweather.data.source.remote.WeatherRemoteDataSource
import com.example.photoweather.domain.repository.WeatherDataRepository
import javax.inject.Inject

class WeatherDataRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherDataRepository {
    override suspend fun getWeatherData(coordinates: Pair<Double, Double>): WeatherDataResponse {
        return weatherRemoteDataSource.getWeatherData(coordinates)
    }
}
