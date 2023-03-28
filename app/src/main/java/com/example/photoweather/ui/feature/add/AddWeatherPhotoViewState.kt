package com.example.photoweather.ui.feature.add

import android.location.Location
import com.example.photoweather.domain.model.WeatherData
import com.example.photoweather.ui.base.ViewState

data class AddWeatherPhotoViewState(
    val loading: Boolean = false,
    val error: Throwable? = null,
    val shouldRequestPermission: Boolean = false,
    val permissionGranted: Boolean? = null,
    val userLocation: Location? = null,
    val weatherData: WeatherData? = null
) : ViewState
