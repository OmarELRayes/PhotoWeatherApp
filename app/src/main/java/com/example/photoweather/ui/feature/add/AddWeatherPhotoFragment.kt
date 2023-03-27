package com.example.photoweather.ui.feature.add

import androidx.fragment.app.viewModels
import com.example.photoweather.R
import com.example.photoweather.common.delegate.viewBinding
import com.example.photoweather.databinding.FragmentAddWeatherPhotoBinding
import com.example.photoweather.ui.base.BaseFragment

class AddWeatherPhotoFragment :
    BaseFragment<AddWeatherPhotoViewModel, FragmentAddWeatherPhotoBinding>(R.layout.fragment_add_weather_photo) {
    override val viewModel: AddWeatherPhotoViewModel by viewModels()
    override val binding: FragmentAddWeatherPhotoBinding by viewBinding()

    override fun observeViewModel() {
    }

    override fun initView() {
        with(binding) {
        }
    }
}
