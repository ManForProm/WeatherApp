package com.example.weatherapp.data.network

import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import com.example.weatherapp.data.network.response.ForecastWeatherResponse
import io.reactivex.rxjava3.core.Observable
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
    ): Observable<CurrentWeatherResponse> = apiService.getCurrentWeather(lat,lon,key,units,language)

    override fun getForecastWeather(
        lat: Int,
        lon: Int,
        key:String,
        units:String,
        language:String
    ):Observable<ForecastWeatherResponse> = apiService.getForecastWeather(lat,lon,key,units,language)
}