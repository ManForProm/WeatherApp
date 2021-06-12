package com.example.weatherapp.data.db.entity

import com.google.gson.annotations.SerializedName

data class Coord(@SerializedName("lon")
                 val lon: Double = 0.0,
                 @SerializedName("lat")
                 val lat: Double = 0.0)