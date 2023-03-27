package com.example.photoweather.ui.feature.list

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.photoweather.R
import com.example.photoweather.common.delegate.viewBinding
import com.example.photoweather.databinding.FragmentWeatherPhotosBinding
import com.example.photoweather.ui.base.BaseFragment

class WeatherPhotosFragment :
    BaseFragment<WeatherPhotosViewModel, FragmentWeatherPhotosBinding>(R.layout.fragment_weather_photos) {

    override val viewModel: WeatherPhotosViewModel by viewModels()
    override val binding: FragmentWeatherPhotosBinding by viewBinding()

    override fun observeViewModel() {
    }

    override fun initView() {
        with(binding) {
            btnAddWeather.setOnClickListener {
                findNavController().navigate(
                    WeatherPhotosFragmentDirections
                        .actionWeatherPhotosFragmentToAddWeatherPhotoFragment(),
                )
            }
        }
    }
}
