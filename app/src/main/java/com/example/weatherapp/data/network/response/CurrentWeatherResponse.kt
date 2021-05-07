package com.example.weatherapp.data.network.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherapp.data.db.entity.Main
import com.example.weatherapp.data.db.entity.Weather
import com.example.weatherapp.data.db.entity.Wind
import com.example.weatherapp.data.db.entity.converters.MainConverter
import com.example.weatherapp.data.db.entity.converters.WeatherConverter
import com.example.weatherapp.data.db.entity.converters.WindConverter

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_weather")
data class CurrentWeatherResponse(
        //@Embedded(prefix = "main_")
        @field:TypeConverters(value = [(MainConverter::class)])
        val main: List<Main> ,
        val name: String? = "",
        val visibility: Int? = 0,
       // @Embedded(prefix = "weather_")
        @field:TypeConverters(value = [(WeatherConverter::class)])
        val weather: List<Weather> ,
        //@Embedded(prefix = "wind_")
        @field:TypeConverters(value = [(WindConverter::class)])
        val wind:List<Wind>
){
    @PrimaryKey(autoGenerate = false)
    var id_current_weather:Int = CURRENT_WEATHER_ID
}