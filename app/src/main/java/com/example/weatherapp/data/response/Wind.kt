package com.example.weatherapp.data.response


import com.google.gson.annotations.SerializedName

data class Wind(
    val deg: Int,
    val gust: Int,
    val speed: Int
)