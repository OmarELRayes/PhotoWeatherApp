package com.example.photoweather.ui.feature.add

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.photoweather.R
import com.example.photoweather.common.delegate.viewBinding
import com.example.photoweather.databinding.FragmentAddWeatherPhotoBinding
import com.example.photoweather.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AddWeatherPhotoFragment : BaseFragment<AddWeatherPhotoViewState, AddWeatherPhotoViewModel, FragmentAddWeatherPhotoBinding>(
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
        state.userLocation?.let {
            Toast.makeText(requireContext(), it.latitude.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleEvents(state: AddWeatherPhotoViewState) {
        if (state.shouldRequestPermission) {
            requestLocationPermission()
        }

        state.error?.let {
            Snackbar.make(requireView(), it.message ?: "Something went wrong", Snackbar.LENGTH_LONG)
        }
    }

    override fun initView() {
        with(binding) {
            imageView.setImageURI(args.imageUri.toUri())
        }
    }
}
