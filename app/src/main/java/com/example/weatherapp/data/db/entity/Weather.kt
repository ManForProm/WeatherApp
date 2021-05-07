package com.example.weatherapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val WEATHER_ID = 0

@Entity(tableName = "weather")
data class Weather(

    val description: String,
    val icon: String,
    @SerializedName("id")
    val idWeather: Int,
    val main: String
){
    @PrimaryKey(autoGenerate = false)
var id_weather:Int = WEATHER_ID

}