package com.example.weatherapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val WIND_ID = 2

@Entity()
data class Wind(
    val deg: Double,
    val gust: Double,
    val speed: Double
){
    @PrimaryKey(autoGenerate = false)
    var id:Int = WIND_ID
}