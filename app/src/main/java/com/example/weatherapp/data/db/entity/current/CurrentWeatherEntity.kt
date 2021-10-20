package com.example.weatherapp.data.db.entity.current

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.data.network.response.current.*

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_weather_table")
data class CurrentWeatherEntity(@ColumnInfo(name = "visibility")
                                  val visibility: Int = 0,
                                @ColumnInfo(name = "timezone")
                                  val timezone: Int = 0,
                                  @ColumnInfo(name = "main")
                                  val main: Main,
                                  @ColumnInfo(name = "clouds")
                                  val clouds: Clouds,
                                  @ColumnInfo(name = "sys")
                                  val sys: Sys,
                                  @ColumnInfo(name = "dt")
                                  val dt: Int = 0,
                                  @ColumnInfo(name = "weather")
                                  val weather: List<WeatherItem>?,
                                  @ColumnInfo(name = "name")
                                  val name: String = "",
                                  @ColumnInfo(name = "cod")
                                  val cod: Int = 0,
                                  @ColumnInfo(name = "id")
                                  val id: Int = 0,
                                  @ColumnInfo(name = "base")
                                  val base: String = "",
                                  @ColumnInfo(name = "wind")
                                  val wind: Wind
){
    @PrimaryKey(autoGenerate = false)
    var id_current_weather:Int = CURRENT_WEATHER_ID
}