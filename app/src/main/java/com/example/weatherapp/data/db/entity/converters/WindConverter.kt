package com.example.weatherapp.data.db.entity.converters

import androidx.room.TypeConverter
import com.example.weatherapp.data.db.entity.Wind
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class WindConverter {
    val gson = Gson()

    @TypeConverter
    fun listMainToString(windList: List<Wind?>?):String?{
        return gson.toJson(windList)
    }
    @TypeConverter
    fun stringToListMain(dataWind:String?):List<Wind?>?{
        if (dataWind ==  null){
            return Collections.emptyList()
        }
        val lsitType: Type = object :
                TypeToken<List<Wind>?>() {}.type
        return gson.fromJson<List<Wind?>>(dataWind,lsitType)
    }}