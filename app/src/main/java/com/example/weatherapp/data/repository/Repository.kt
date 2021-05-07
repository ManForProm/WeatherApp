package com.example.weatherapp.data.repository

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

        //val checkNetworkConnection: Boolean = isConnected(context)

        return  apiHelper.getCurrentWeather(
            "London",
            API_KEY,
            "metric",
            Locale.getDefault().displayLanguage
        )
    }
    /*fun upsertCurrentMain(main: Main){
        currentWeatherDao.upsertCurrentMain(main)
    }
    fun upsertCurrentWeather(weather: Weather){
        currentWeatherDao.upsertCurrentWeather(weather)
    }*/
    fun upsertCurrentWeather(currentWeather: CurrentWeatherResponse){
        currentWeatherDao.upsertCurrentWeather(currentWeather)
    }
    fun getCurrentWeatherFromDB(): Observable<CurrentWeatherResponse>{
        return currentWeatherDao.getCurrentWeatherDB()
    }
   /* fun getWeatherFromDB(): Observable<ArrayList<Weather>>{
        return currentWeatherDao.getWeatherDB()
    }
    fun getCurrentMainFromDB():Observable<ArrayList<Main>>{
        return currentWeatherDao.getCurrentMainDB()
    }*/
}
