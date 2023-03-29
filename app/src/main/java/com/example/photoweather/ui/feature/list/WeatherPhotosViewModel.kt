package com.example.photoweather.ui.feature.list

import androidx.lifecycle.viewModelScope
import com.example.photoweather.domain.usecase.GetWeatherPhotosUseCase
import com.example.photoweather.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherPhotosViewModel @Inject constructor(
    private val getWeatherPhotosUseCase: GetWeatherPhotosUseCase
) : BaseViewModel<WeatherPhotosViewState>() {
    override val defaultViewState: WeatherPhotosViewState
        get() = WeatherPhotosViewState()

    init {
        loadWeatherPhotos()
    }

    private fun loadWeatherPhotos() {
        viewModelScope.launch {
            getWeatherPhotosUseCase().collect {
                _viewStates.value = _viewStates.value.copy(
                    isEmpty = it.isEmpty(),
                    weatherPhotos = it
                )
            }
        }
    }
}
