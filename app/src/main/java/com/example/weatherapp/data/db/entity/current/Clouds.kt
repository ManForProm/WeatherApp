package com.example.weatherapp.data.db.entity.current

import com.google.gson.annotations.SerializedName

data class Clouds(@SerializedName("all")
                  val all: Int = 0)