package com.example.photoweather.ui.feature.list

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.photoweather.R
import com.example.photoweather.common.FileHandler
import com.example.photoweather.common.FileHandler.createImageFile
import com.example.photoweather.common.delegate.viewBinding
import com.example.photoweather.databinding.FragmentWeatherPhotosBinding
import com.example.photoweather.ui.base.BaseFragment

class WeatherPhotosFragment :
    BaseFragment<WeatherPhotosViewModel, FragmentWeatherPhotosBinding>(R.layout.fragment_weather_photos) {

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
    }

    override fun initView() {
        with(binding) {
            btnAddWeather.setOnClickListener {
                takePhoto()
                /*
                findNavController().navigate(
                    WeatherPhotosFragmentDirections
                        .actionWeatherPhotosFragmentToAddWeatherPhotoFragment(),
                )*/
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
                    photoFile!!,
                ),
            )
            activityResult.launch(takePictureIntent)
        }
    }

    private fun navigateToAddWeatherPhoto(uri: Uri) {
        findNavController().navigate(
            WeatherPhotosFragmentDirections
                .actionWeatherPhotosFragmentToAddWeatherPhotoFragment(uri.toString()),
        )
    }
}
