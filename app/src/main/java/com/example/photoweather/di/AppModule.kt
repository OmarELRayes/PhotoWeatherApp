/*
 *  Created by Omar ELRayes@CresignZone on 3/21/22, 1:18 PM
 *  Copyright (c) 2022 . All rights reserved.
 *  Last modified 3/20/22, 10:28 PM
 */

package com.example.photoweather.di

import android.content.Context
import androidx.room.Room
import com.example.photoweather.data.source.local.WeatherPhotoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun provideWeatherItemDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, WeatherPhotoDatabase::class.java, "weather_photo_db")
            .build()

    @Singleton
    @Provides
    fun provideWeatherHistoryDao(database: WeatherPhotoDatabase) = database.weatherPhotoDao()
}
