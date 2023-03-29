package com.example.photoweather.data.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class WeatherDataResponse(
    @SerialName("coord")
    val coordinates: Coordinates,
    val main: Main,
    val name: String,
    val sys: Sys?,
    val visibility: Int?,
    val weather: List<Weather>,
    val wind: Wind?
) {

    @kotlinx.serialization.Serializable
    data class Coordinates(
        val lat: Double,
        val lon: Double
    )

    @kotlinx.serialization.Serializable
    data class Main(
        @SerialName("feels_like")
        val feelsLike: Double?,
        @SerialName("grnd_level")
        val groundLevel: Int?,
        val humidity: Int?,
        val pressure: Int?,
        @SerialName("sea_level")
        val seaLevel: Int?,
        @SerialName("temp")
        val temp: Double?,
        @SerialName("temp_max")
        val tempMax: Double?,
        @SerialName("temp_min")
        val tempMin: Double?
    )

    @kotlinx.serialization.Serializable
    data class Sys(
        val country: String?,
        @SerialName("sunrise")
        val sunriseTime: Int?,
        @SerialName("sunset")
        val sunsetTime: Int?
    )

    @kotlinx.serialization.Serializable
    data class Weather(
        val id: Int?,
        val description: String?,
        val icon: String?,
        val main: String?
    )

    @kotlinx.serialization.Serializable
    data class Wind(
        @SerialName("deg")
        val degree: Int?,
        val gust: Double?,
        val speed: Double?
    )
}
