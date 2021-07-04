package com.example.weatherapp.data.db.entity.converters

import androidx.room.TypeConverter
import com.example.weatherapp.data.db.entity.current.Wind
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class WindConverter {
    val gson = Gson()

    @TypeConverter
    fun listMainToString(wind: Wind?):String?{
        return gson.toJson(wind)
    }
    @TypeConverter
    fun stringToListMain(dataWind:String?): Wind?{
        val lsitType: Type = object :
                TypeToken<Wind?>() {}.type
        return gson.fromJson<Wind?>(dataWind,lsitType)
    }}