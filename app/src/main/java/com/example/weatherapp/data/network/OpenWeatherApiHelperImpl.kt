package com.example.weatherapp.data.network

import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import com.example.weatherapp.data.network.response.ForecastWeatherResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class OpenWeatherApiHelperImpl @Inject constructor(
    private val apiService: OpenWeatherApiService
):OpenWeatherApiHelper {
    override fun getCurrentWeather(
        location: String,
        key: String,
        units: String,
        language: String
    ): Observable<CurrentWeatherResponse> = apiService.getCurrentWeather(location,key,units,language)

    override fun getForecastWeather(
        location:String,
        key:String,
        units:String,
        language:String
    ):Observable<ForecastWeatherResponse> = apiService.getForecastWeather(location,key,units,language)
}