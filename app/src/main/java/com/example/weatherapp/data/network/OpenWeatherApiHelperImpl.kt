package com.example.weatherapp.data.network

import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import com.example.weatherapp.data.network.response.ForecastWeatherResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class OpenWeatherApiHelperImpl @Inject constructor(
    private val apiService: OpenWeatherApiService
):OpenWeatherApiHelper {
    override fun getCurrentWeather(
        lat: Double,
        lon: Double,
        key: String,
        units: String,
        language: String
    ): Single<CurrentWeatherResponse> = apiService.getCurrentWeather(lat,lon,key,units,language)

    override fun getForecastWeather(
        lat: Double,
        lon: Double,
        key:String,
        units:String,
        language:String
    ):Observable<ForecastWeatherResponse> = apiService.getForecastWeather(lat,lon,key,units,language)
}