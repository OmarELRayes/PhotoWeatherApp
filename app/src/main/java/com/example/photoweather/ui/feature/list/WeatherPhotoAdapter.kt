package com.example.photoweather.ui.feature.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.photoweather.R
import com.example.photoweather.databinding.ItemWeatherPhotoBinding
import com.example.photoweather.domain.model.WeatherPhoto

class WeatherPhotoAdapter(private val onItemClick: (WeatherPhoto) -> Unit) :
    ListAdapter<WeatherPhoto, WeatherPhotoAdapter.WeatherPhotoViewHolder>(
        WeatherPhotoDifferentiator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WeatherPhotoViewHolder(
        ItemWeatherPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: WeatherPhotoViewHolder, position: Int) {
        holder.bind(getItem(position) as WeatherPhoto)
    }

    inner class WeatherPhotoViewHolder(private val binding: ItemWeatherPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherPhoto) {
            with(binding) {
                val context = binding.root.context
                root.setOnClickListener { onItemClick(item) }
                imageView.setImageURI(item.image.toUri())
                humidity.text =
                    context.getString(
                        R.string.add_weather_humidity,
                        item.humidity.toString()
                    )
                temperature.text =
                    context.getString(
                        R.string.add_weather_temperature,
                        item.temperature.toString()
                    )
                country.text = context.getString(R.string.add_weather_country, item.country)
                name.text = context.getString(R.string.add_weather_name, item.name)
                description.text =
                    context.getString(
                        R.string.add_weather_weather_condition,
                        item.description
                    )
            }
        }
    }

    private class WeatherPhotoDifferentiator : DiffUtil.ItemCallback<WeatherPhoto>() {

        override fun areItemsTheSame(oldItem: WeatherPhoto, newItem: WeatherPhoto) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: WeatherPhoto, newItem: WeatherPhoto): Boolean {
            return oldItem == newItem
        }
    }
}
