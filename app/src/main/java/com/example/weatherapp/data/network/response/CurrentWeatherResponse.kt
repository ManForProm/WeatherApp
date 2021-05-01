package com.example.weatherapp.data.network.response

import com.example.weatherapp.data.db.entity.Main
import com.example.weatherapp.data.db.entity.Weather
import com.example.weatherapp.data.db.entity.Wind

data class CurrentWeatherResponse(
        val main: Main,
        val name: String,
        val visibility: Int,
        val weather: List<Weather>,
        val wind: Wind
)