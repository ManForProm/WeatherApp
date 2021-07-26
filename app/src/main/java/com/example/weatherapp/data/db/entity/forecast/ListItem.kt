package com.example.weatherapp.data.db.entity.forecast

import androidx.room.TypeConverters
import com.example.weatherapp.data.db.entity.converters.MainConverter
import com.google.gson.annotations.SerializedName

data class ListItem(@SerializedName("dt")
                    val dt: Int = 0,
                    @SerializedName("dt_txt")
                    val dtTxt: String = "",
                    @SerializedName("weather")
                    val weather: List<WeatherItem>?,
                    @SerializedName("main")
                    @TypeConverters(MainConverter::class)
                    val main: Main)