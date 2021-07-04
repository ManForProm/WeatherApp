package com.example.weatherapp.data.db.entity.converters

import androidx.room.TypeConverter
import com.example.weatherapp.data.db.entity.current.WeatherItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class WeatherConverter {

    val gson = Gson()

    @TypeConverter
    fun listWeatherToString(weatherList: List<WeatherItem?>?):String?{
        return gson.toJson(weatherList)
    }
    @TypeConverter
    fun stringToListWeather(dataWeather:String?):List<WeatherItem?>?{
        if (dataWeather ==  null){
            return Collections.emptyList()
        }
        val lsitType: Type = object :
            TypeToken<List<WeatherItem>?>() {}.type
        return gson.fromJson<List<WeatherItem?>>(dataWeather,lsitType)
    }
}