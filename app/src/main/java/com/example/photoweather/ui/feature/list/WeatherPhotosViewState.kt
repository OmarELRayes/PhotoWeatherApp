package com.example.photoweather.ui.feature.list

import com.example.photoweather.domain.model.WeatherPhoto
import com.example.photoweather.ui.base.ViewState

data class WeatherPhotosViewState(
    val isEmpty: Boolean = true,
    val weatherPhotos: List<WeatherPhoto> = listOf()
) : ViewState
