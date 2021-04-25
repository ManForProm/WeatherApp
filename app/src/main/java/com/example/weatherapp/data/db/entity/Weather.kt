package com.example.weatherapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val WEATHER_ID = 1

@Entity()
data class Weather(

    val description: String,
    val icon: String,
    @SerializedName("id")
    val idWeather: Int,
    val main: String
){
    @PrimaryKey(autoGenerate = false)
    var id:Int = WIND_ID
}