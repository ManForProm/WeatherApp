package com.example.weatherapp.data.db.entity

import com.google.gson.annotations.SerializedName

data class Weather(

    val description: String,
    val icon: String,
    @SerializedName("id")
    val idWeather: Int,
    val main: String
)