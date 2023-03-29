package com.example.photoweather.ui.feature.add

import android.location.Location
import androidx.lifecycle.viewModelScope
import com.example.photoweather.domain.usecase.AddWeatherPhotoUseCase
import com.example.photoweather.domain.usecase.GetUserLocationUseCase
import com.example.photoweather.domain.usecase.GetWeatherDataUseCase
import com.example.photoweather.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AddWeatherPhotoViewModel @Inject constructor(
    private val getUserLocationUseCase: GetUserLocationUseCase,
    private val getWeatherDataUseCase: GetWeatherDataUseCase,
    private val addWeatherPhotoUseCase: AddWeatherPhotoUseCase
) : BaseViewModel<AddWeatherPhotoViewState>() {

    override val defaultViewState: AddWeatherPhotoViewState
        get() = AddWeatherPhotoViewState()

    fun shouldRequestPermission(locationPermissionGranted: Boolean) {
        if (locationPermissionGranted) {
            getUserLocation()
        }
        _viewStates.value =
            _viewStates.value.copy(shouldRequestPermission = !locationPermissionGranted)
    }

    private fun getUserLocation() {
        viewModelScope.launch {
            _viewStates.value = defaultViewState.copy(loading = true)
            getUserLocationUseCase().collect {
                if (it != null) getWeatherData(it)
            }
        }
    }

    private fun getWeatherData(location: Location) {
        viewModelScope.launch {
            try {
                val data =
                    getWeatherDataUseCase(coordinates = location.latitude to location.longitude)
                _viewStates.value = _viewStates.value.copy(loading = false, weatherData = data)
            } catch (e: Exception) {
                _viewStates.value = _viewStates.value.copy(loading = false, error = e)
            }
        }
    }

    fun saveWeatherPhoto(image: String) {
        viewModelScope.launch {
            _viewStates.value.weatherData?.let { addWeatherPhotoUseCase.invoke(it, image) }
        }
    }
}
