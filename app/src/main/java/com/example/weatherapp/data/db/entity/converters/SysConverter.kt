package com.example.weatherapp.data.db.entity.converters

import androidx.room.TypeConverter
import com.example.weatherapp.data.network.response.current.Sys
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SysConverter {
    val gson = Gson()

    @TypeConverter
    fun sysToString(sys: Sys?):String?{
        return gson.toJson(sys)
    }
    @TypeConverter
    fun stringToSys(dataSys:String?): Sys?{
        val listType: Type = object :
            TypeToken<Sys?>() {}.type
        return gson.fromJson<Sys?>(dataSys,listType)
    }
}