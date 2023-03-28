package com.example.photoweather.di

import com.example.photoweather.data.repository.WeatherDataRepositoryImpl
import com.example.photoweather.domain.repository.WeatherDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    @ViewModelScoped
    fun provideWeatherDataRepository(
        weatherDataRepository: WeatherDataRepositoryImpl
    ): WeatherDataRepository
}
