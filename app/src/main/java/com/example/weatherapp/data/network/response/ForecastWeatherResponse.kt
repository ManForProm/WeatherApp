package com.example.weatherapp.data.network.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.data.db.entity.forecast.City
import com.example.weatherapp.data.db.entity.forecast.ListItem
import com.google.gson.annotations.SerializedName

const val FORECAST_WEATHER_ID = 1

@Entity(tableName = "forecast_weather")
data class ForecastWeatherResponse(@SerializedName("city")
                                   val city: City,
                                   @SerializedName("cnt")
                                   val cnt: Int = 0,
                                   @SerializedName("cod")
                                   val cod: String = "",
                                   @SerializedName("message")
                                   val message: Int = 0,
                                   @SerializedName("list")
                                   val list: List<ListItem>?){
    @PrimaryKey(autoGenerate = false)
    var id_forecast_weather:Int = FORECAST_WEATHER_ID
}