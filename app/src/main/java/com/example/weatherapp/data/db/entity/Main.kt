package com.example.weatherapp.data.db.entity


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


const val MAIN_ID = 0

@Entity(tableName = "main")
data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
   /* @Embedded(prefix = "weather_")
    @TypeConverters(WeatherConverter::class)
    val Weather:ArrayList<Weather>,*/
    @Embedded(prefix = "wind")
    val Wind:Wind
){ @PrimaryKey(autoGenerate = false)
    var id_main:Int = MAIN_ID
}