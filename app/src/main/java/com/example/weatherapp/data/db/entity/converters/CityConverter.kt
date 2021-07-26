package com.example.weatherapp.data.db.entity.converters

import androidx.room.TypeConverter
import com.example.weatherapp.data.db.entity.forecast.City
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class CityConverter {
    val gson = Gson()

    @TypeConverter
    fun cityToString(city: City?):String?{
        return gson.toJson(city)
    }
    @TypeConverter
    fun stringToCity(dataCity:String?): City?{
        val listType: Type = object :
            TypeToken<City?>() {}.type
        return gson.fromJson<City?>(dataCity,listType)
    }
}