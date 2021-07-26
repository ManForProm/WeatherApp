package com.example.weatherapp.data.db.entity.forecast

import com.google.gson.annotations.SerializedName

data class City(@SerializedName("country")
                val country: String = "",
                @SerializedName("sunrise")
                val sunrise: Int = 0,
                @SerializedName("timezone")
                val timezone: Int = 0,
                @SerializedName("sunset")
                val sunset: Int = 0,
                @SerializedName("name")
                val name: String = "",
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("population")
                val population: Int = 0)