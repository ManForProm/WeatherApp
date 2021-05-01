package com.example.weatherapp.data.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.db.entity.Main
import com.example.weatherapp.data.db.unitlocalized.CurrentWeatherDao
import com.example.weatherapp.data.network.OpenWeatherApiHelper
import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import io.reactivex.rxjava3.core.Observable
import java.util.*
import javax.inject.Inject

const val API_KEY = "704ff0f1c1c358807a94555e07da85f2"

class Repository @Inject constructor(
    private val apiHelper: OpenWeatherApiHelper,
    private val currentWeatherDao: CurrentWeatherDao
){
    fun getCurrentWeather(): Observable<CurrentWeatherResponse>{
        return apiHelper.getCurrentWeather("London", API_KEY,"metric",Locale.getDefault().displayLanguage)
    }
    fun upsertCurrentWeather(currentWeatherResponse: CurrentWeatherResponse){
        currentWeatherDao.upsertCurrentWeather(currentWeatherResponse)
    }
    fun getCurrentWeatherFromDB(): LiveData<List<Main>>{
        return currentWeatherDao.getCurrentWeatherDB()
    }
}