package com.example.photoweather.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WeatherPhotos")
data class WeatherPhoto(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val image: String,
    val description: String,
    val temperature: Double,
    val humidity: Int,
    val country: String,
    val name: String
)
