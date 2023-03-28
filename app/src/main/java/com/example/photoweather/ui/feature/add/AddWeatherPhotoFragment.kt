package com.example.photoweather.ui.feature.add

import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.photoweather.R
import com.example.photoweather.common.delegate.viewBinding
import com.example.photoweather.databinding.FragmentAddWeatherPhotoBinding
import com.example.photoweather.ui.base.BaseFragment

class AddWeatherPhotoFragment :
    BaseFragment<AddWeatherPhotoViewModel, FragmentAddWeatherPhotoBinding>(R.layout.fragment_add_weather_photo) {
    override val viewModel: AddWeatherPhotoViewModel by viewModels()
    override val binding: FragmentAddWeatherPhotoBinding by viewBinding()

    private val args: AddWeatherPhotoFragmentArgs by navArgs()

    override fun observeViewModel() {
    }

    override fun initView() {
        with(binding) {
            imageView.setImageURI(args.imageUri.toUri())
        }
    }
}
