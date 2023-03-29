package com.example.photoweather.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.photoweather.domain.model.WeatherPhoto

@Database(entities = [WeatherPhoto::class], version = 1)
abstract class WeatherPhotoDatabase : RoomDatabase() {
    abstract fun weatherPhotoDao(): WeatherPhotoDao
}
