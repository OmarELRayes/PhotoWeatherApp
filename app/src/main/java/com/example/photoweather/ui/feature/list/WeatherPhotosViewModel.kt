package com.example.photoweather.ui.feature.list

import com.example.photoweather.ui.base.BaseViewModel

class WeatherPhotosViewModel : BaseViewModel<WeatherPhotosViewState>() {
    override val defaultViewState: WeatherPhotosViewState
        get() = WeatherPhotosViewState()
}
