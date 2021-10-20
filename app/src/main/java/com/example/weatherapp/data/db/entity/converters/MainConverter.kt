package com.example.weatherapp.data.db.entity.converters

import androidx.room.TypeConverter
import com.example.weatherapp.data.network.response.current.Main
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainConverter {
    val gson = Gson()

    @TypeConverter
    fun mainToString(main: Main?):String?{
        return gson.toJson(main)
    }
    @TypeConverter
    fun stringToMain(dataMain:String?): Main?{
        val listType: Type = object :
                TypeToken<Main?>() {}.type
        return gson.fromJson<Main?>(dataMain,listType)
    }
}