package com.example.photoweather.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "WeatherPhotos")
@Parcelize
data class WeatherPhoto(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val image: String,
    val description: String,
    val temperature: Double,
    val humidity: Int,
    val country: String,
    val name: String
) : Parcelable
