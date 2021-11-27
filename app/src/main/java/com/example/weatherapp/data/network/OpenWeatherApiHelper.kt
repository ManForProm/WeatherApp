package com.example.weatherapp.data.network

import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import com.example.weatherapp.data.network.response.ForecastWeatherResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface OpenWeatherApiHelper {
    fun getCurrentWeather(
        lat: Double,
        lon: Double,
        key:String,
        units:String,
        language:String
    ): Single<CurrentWeatherResponse>

    fun getForecastWeather(
        lat: Double,
        lon: Double,
        key:String,
        units:String,
        language:String
    ):Observable<ForecastWeatherResponse>

}