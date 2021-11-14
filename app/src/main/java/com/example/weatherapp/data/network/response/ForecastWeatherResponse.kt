package com.example.weatherapp.data.network.response

import com.example.weatherapp.data.network.response.forecast.City
import com.example.weatherapp.data.network.response.forecast.ListItem
import com.google.gson.annotations.SerializedName


data class ForecastWeatherResponse(@SerializedName("city")
                                   val city: City,
                                   @SerializedName("cnt")
                                   val cnt: Int = 0,
                                   @SerializedName("cod")
                                   val cod: String = "",
                                   @SerializedName("message")
                                   val message: Int = 0,
                                   @SerializedName("list")
                                   val list: List<ListItem>?)