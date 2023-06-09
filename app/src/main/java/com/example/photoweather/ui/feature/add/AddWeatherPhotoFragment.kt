package com.example.photoweather.ui.feature.add

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.photoweather.R
import com.example.photoweather.common.delegate.viewBinding
import com.example.photoweather.common.extensions.shareImage
import com.example.photoweather.databinding.FragmentAddWeatherPhotoBinding
import com.example.photoweather.domain.model.WeatherData
import com.example.photoweather.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AddWeatherPhotoFragment :
    BaseFragment<AddWeatherPhotoViewState, AddWeatherPhotoViewModel, FragmentAddWeatherPhotoBinding>(
        R.layout.fragment_add_weather_photo
    ) {
    override val viewModel: AddWeatherPhotoViewModel by viewModels()
    override val binding: FragmentAddWeatherPhotoBinding by viewBinding()

    private val args: AddWeatherPhotoFragmentArgs by navArgs()

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            if (!it.containsValue(false)) {
                viewModel.shouldRequestPermission(
                    locationPermissionGranted = true
                )
            }
        }

    override fun observeViewModel() {
        viewModel.viewStates
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { value -> handleViewStates(value) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.viewEvents
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { value -> handleEvents(value) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.shouldRequestPermission(isLocationPermissionGranted())
    }

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission() {
        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun handleViewStates(state: AddWeatherPhotoViewState) {
        state.weatherData?.let {
            updateWeatherData(it)
        }

        binding.progressBar.isVisible = state.loading
    }

    private fun updateWeatherData(weatherData: WeatherData) {
        with(binding) {
            groupWeatherData.isVisible = true
            btnShare.isEnabled = true
            btnConfirm.isEnabled = true
            humidity.text =
                getString(R.string.add_weather_humidity, weatherData.humidity.toString())
            temperature.text =
                getString(R.string.add_weather_temperature, weatherData.temperature.toString())
            country.text = getString(R.string.add_weather_country, weatherData.country)
            name.text = getString(R.string.add_weather_name, weatherData.name)
            description.text =
                getString(R.string.add_weather_weather_condition, weatherData.description)
        }
    }

    private fun handleEvents(state: AddWeatherPhotoViewState) {
        if (state.shouldRequestPermission) {
            requestLocationPermission()
        }

        state.error?.let {
            Snackbar.make(requireView(), it.message ?: "Something went wrong", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun initView() {
        with(binding) {
            imageView.setImageURI(args.imageUri.toUri())

            btnShare.setOnClickListener {
                shareImage(content)
            }

            btnConfirm.setOnClickListener {
                viewModel.saveWeatherPhoto(args.imageUri)
                findNavController().navigateUp()
            }
        }
    }
}
