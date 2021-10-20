package com.example.weatherapp.data.network.response.current

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Main")
data class Main(
                @SerializedName("temp")
                val temp: Double = 0.0,
                @SerializedName("temp_min")
                val tempMin: Double = 0.0,
                @SerializedName("humidity")
                val humidity: Int = 0,
                @SerializedName("pressure")
                val pressure: Int = 0,
                @SerializedName("feels_like")
                val feelsLike: Double = 0.0,
                @SerializedName("temp_max")
                val tempMax: Double = 0.0)