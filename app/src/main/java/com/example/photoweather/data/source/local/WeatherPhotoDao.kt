package com.example.photoweather.data.source.local

import androidx.room.*
import com.example.photoweather.domain.model.WeatherPhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherPhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWeatherPhoto(weatherPhoto: WeatherPhoto)

    @Query("SELECT * FROM WeatherPhotos")
    fun getWeatherPhotos(): Flow<List<WeatherPhoto>>
}
