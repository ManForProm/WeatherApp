package com.example.weatherapp.data.db.entity.converters

import androidx.room.TypeConverter
import com.example.weatherapp.data.db.entity.Coord
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class CoordConverter {
    val gson = Gson()

    @TypeConverter
    fun сoordToString(сoord: Coord?):String?{
        return gson.toJson(сoord)
    }
    @TypeConverter
    fun stringToCoord(dataCoord:String?): Coord?{
        val listType: Type = object :
            TypeToken<Coord?>() {}.type
        return gson.fromJson<Coord?>(dataCoord,listType)
    }
}