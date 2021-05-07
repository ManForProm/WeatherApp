package com.example.weatherapp.data.db.entity.converters

import androidx.room.TypeConverter
import com.example.weatherapp.data.db.entity.Main
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class MainConverter {
    val gson = Gson()

    @TypeConverter
    fun listMainToString(mainList: List<Main?>?):String?{
        return gson.toJson(mainList)
    }
    @TypeConverter
    fun stringToListMain(dataMain:String?):List<Main?>?{
        if (dataMain ==  null){
            return Collections.emptyList()
        }
        val lsitType: Type = object :
                TypeToken<List<Main>?>() {}.type
        return gson.fromJson<List<Main?>>(dataMain,lsitType)
    }
}