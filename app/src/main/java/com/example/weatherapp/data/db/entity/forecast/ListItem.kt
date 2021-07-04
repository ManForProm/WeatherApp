package com.example.weatherapp.data.db.entity.forecast

import com.google.gson.annotations.SerializedName

data class ListItem(@SerializedName("dt")
                    val dt: Int = 0,
                    @SerializedName("dt_txt")
                    val dtTxt: String = "",
                    @SerializedName("weather")
                    val weather: List<WeatherItem>?,
                    @SerializedName("main")
                    val main: Main)