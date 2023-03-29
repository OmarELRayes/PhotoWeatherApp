package com.example.photoweather.ui.feature.details

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.photoweather.R
import com.example.photoweather.common.delegate.viewBinding
import com.example.photoweather.common.extensions.shareImage
import com.example.photoweather.databinding.FragmentFullScreenPhotoBinding
import com.example.photoweather.domain.model.WeatherPhoto

class FullScreenPhotoFragment : Fragment(R.layout.fragment_full_screen_photo) {

    private val binding: FragmentFullScreenPhotoBinding by viewBinding()
    private val args: FullScreenPhotoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateUI(args.weatherPhoto)
    }

    private fun updateUI(weatherPhoto: WeatherPhoto) {
        with(binding) {
            imageView.setImageURI(weatherPhoto.image.toUri())
            humidity.text =
                getString(R.string.add_weather_humidity, weatherPhoto.humidity.toString())
            temperature.text =
                getString(R.string.add_weather_temperature, weatherPhoto.temperature.toString())
            country.text = getString(R.string.add_weather_country, weatherPhoto.country)
            name.text = getString(R.string.add_weather_name, weatherPhoto.name)
            description.text =
                getString(R.string.add_weather_weather_condition, weatherPhoto.description)

            btnShare.setOnClickListener {
                shareImage(content)
            }
        }
    }
}
