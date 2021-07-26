package com.example.weatherapp.data.db.entity.converters

import androidx.room.TypeConverter
import com.example.weatherapp.data.db.entity.forecast.ListItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ListItemConverter {
    val gson = Gson()

    @TypeConverter
    fun cloudsToString(listItem: List<ListItem>?):String?{
        return gson.toJson(listItem)
    }
    @TypeConverter
    fun stringToClouds(dataListItem:String?): List<ListItem>?{
        val listType: Type = object :
            TypeToken<List<ListItem>?>() {}.type
        return gson.fromJson<List<ListItem>?>(dataListItem,listType)
    }
}