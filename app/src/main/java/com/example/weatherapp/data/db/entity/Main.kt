package com.example.weatherapp.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val MAIN_PARAMETRS_ID = 0

@Entity(tableName = "main_parametrs")
data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double
){
    @PrimaryKey(autoGenerate = false)
    var id:Int = WIND_ID
}