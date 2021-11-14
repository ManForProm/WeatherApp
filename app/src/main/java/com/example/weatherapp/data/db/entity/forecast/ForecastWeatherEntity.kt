package com.example.weatherapp.data.db.entity.forecast

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.data.network.response.forecast.City
import com.example.weatherapp.data.network.response.forecast.ListItem

const val FORECAST_WEATHER_ID = 0

@Entity(tableName = "forecast_weather")
class ForecastWeatherEntity (
    @ColumnInfo(name = "city")
    val city: City,
    @ColumnInfo(name = "cnt")
    val cnt: Int = 0,
    @ColumnInfo(name = "cod")
    val cod: String = "",
    @ColumnInfo(name = "message")
    val message: Int = 0,
    @ColumnInfo(name = "list")
    val list: List<ListItem>?){
        @PrimaryKey(autoGenerate = false)
        var id_forecast_weather:Int = FORECAST_WEATHER_ID
}