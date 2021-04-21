package com.example.weatherapp.data

import retrofit2.http.GET
import retrofit2.http.Query

//http://api.openweathermap.org/data/2.5/weather?q=London&appid=704ff0f1c1c358807a94555e07da85f2&lang=en

const val API_KEY = "704ff0f1c1c358807a94555e07da85f2"

interface OpenWeatherApiService {

    @GET("weather")
    fun getCurrentWeather(
        @Query("q") location:String,
        @Query("lang") language:String = "en"
    ):Observable<List<CurrentWeatherResponse>>
    companion object {
        operator fun invoke():OpenWeatherApiService{
            val requestInterc
        }
    }
}