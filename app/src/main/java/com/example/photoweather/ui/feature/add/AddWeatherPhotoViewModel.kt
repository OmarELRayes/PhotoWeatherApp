package com.example.photoweather.ui.feature.add

import androidx.lifecycle.viewModelScope
import com.example.photoweather.domain.usecase.GetUserLocationUseCase
import com.example.photoweather.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AddWeatherPhotoViewModel @Inject constructor(
    private val getUserLocationUseCase: GetUserLocationUseCase
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
            getUserLocationUseCase().collect {
                _viewStates.value = _viewStates.value.copy(userLocation = it)
                if (it != null) getWeatherData()
            }
        }
    }

    private fun getWeatherData() {
    }
}
