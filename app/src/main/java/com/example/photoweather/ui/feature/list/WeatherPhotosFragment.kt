package com.example.photoweather.ui.feature.list

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.photoweather.R
import com.example.photoweather.common.FileHandler
import com.example.photoweather.common.FileHandler.createImageFile
import com.example.photoweather.common.delegate.viewBinding
import com.example.photoweather.databinding.FragmentWeatherPhotosBinding
import com.example.photoweather.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class WeatherPhotosFragment :
    BaseFragment<WeatherPhotosViewState, WeatherPhotosViewModel, FragmentWeatherPhotosBinding>(
        R.layout.fragment_weather_photos
    ) {

    override val viewModel: WeatherPhotosViewModel by viewModels()
    override val binding: FragmentWeatherPhotosBinding by viewBinding()

    private val photoFile by lazy {
        createImageFile()
    }

    private val activityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = Uri.fromFile(photoFile?.absoluteFile)
                navigateToAddWeatherPhoto(uri)
            }
        }

    override fun observeViewModel() {
        viewModel.viewStates
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { value -> handleViewStates(value) }
            .launchIn(lifecycleScope)
    }

    private fun handleViewStates(state: WeatherPhotosViewState) {
        binding.emptyView.root.isVisible = state.isEmpty
    }

    override fun initView() {
        with(binding) {
            btnAddWeather.setOnClickListener {
                takePhoto()
            }
        }
    }

    private fun takePhoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (photoFile != null) {
            takePictureIntent.putExtra(
                MediaStore.EXTRA_OUTPUT,
                FileHandler.getUri(
                    requireContext(),
                    photoFile!!
                )
            )
            activityResult.launch(takePictureIntent)
        }
    }

    private fun navigateToAddWeatherPhoto(uri: Uri) {
        findNavController().navigate(
            WeatherPhotosFragmentDirections
                .actionWeatherPhotosFragmentToAddWeatherPhotoFragment(uri.toString())
        )
    }
}
