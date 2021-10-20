package com.example.weatherapp.data.db.entity.converters

import androidx.room.TypeConverter
import com.example.weatherapp.data.network.response.current.Clouds
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class CloudsConverter {
    val gson = Gson()

    @TypeConverter
    fun cloudsToString(clouds: Clouds?):String?{
        return gson.toJson(clouds)
    }
    @TypeConverter
    fun stringToClouds(dataClouds:String?): Clouds?{
        val listType: Type = object :
            TypeToken<Clouds?>() {}.type
        return gson.fromJson<Clouds?>(dataClouds,listType)
    }
}