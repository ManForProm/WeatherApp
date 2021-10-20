package com.example.weatherapp.data.network.response

import androidx.room.Entity
import com.example.weatherapp.data.network.response.current.Clouds
import com.example.weatherapp.data.network.response.current.Sys
import com.example.weatherapp.data.network.response.current.Wind
import com.google.gson.annotations.SerializedName


@Entity(tableName = "current_weather")
data class CurrentWeatherResponse(@SerializedName("visibility")
                                  val visibility: Int = 0,
                                  @SerializedName("timezone")
                                  val timezone: Int = 0,
                                  @SerializedName("main")
                                  val main: com.example.weatherapp.data.network.response.current.Main,
                                  @SerializedName("clouds")
                                  val clouds: Clouds,
                                  @SerializedName("sys")
                                  val sys: Sys,
                                  @SerializedName("dt")
                                  val dt: Int = 0,
                                  @SerializedName("weather")
                                  val weather: List<com.example.weatherapp.data.network.response.current.WeatherItem>?,
                                  @SerializedName("name")
                                  val name: String = "",
                                  @SerializedName("cod")
                                  val cod: Int = 0,
                                  @SerializedName("id")
                                  val id: Int = 0,
                                  @SerializedName("base")
                                  val base: String = "",
                                  @SerializedName("wind")
                                  val wind: Wind
        )