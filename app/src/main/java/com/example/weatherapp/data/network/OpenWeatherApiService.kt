package com.example.weatherapp.data.network

import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import com.example.weatherapp.data.network.response.ForecastWeatherResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


//http://api.openweathermap.org/data/2.5/weather?q=London&appid=704ff0f1c1c358807a94555e07da85f2&lang=en


interface OpenWeatherApiService {

    @GET("weather")
    fun getCurrentWeather(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("appid") key:String,
        @Query("units") units:String,
        @Query("lang") language:String = "en"
    ):Observable<CurrentWeatherResponse>

    @GET("forecast")
    fun getForecastWeather(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("appid") key:String,
        @Query("units") units:String,
        @Query("lang") language:String = "en"
    ):Observable<ForecastWeatherResponse>
}