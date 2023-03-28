package com.example.photoweather.data.source.remote

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class WeatherRemoteDataSource @Inject constructor(
    private val serviceGenerator: ServiceGenerator,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getWeatherData(coordinates: Pair<Double, Double>) = with(dispatcher) {
        return@with serviceGenerator.createService(WeatherApi::class.java)
            .getWeatherData(coordinates.first, coordinates.second)
    }
}
