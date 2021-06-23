package com.example.weatherapp.data.repository

import com.example.weatherapp.data.db.unitlocalized.CurrentWeatherDao
import com.example.weatherapp.data.network.OpenWeatherApiHelper
import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


const val API_KEY = "704ff0f1c1c358807a94555e07da85f2"

class Repository @Inject constructor(
    private val apiHelper: OpenWeatherApiHelper,
    private val currentWeatherDao: CurrentWeatherDao
){
    private  val compositeDisposable = CompositeDisposable()
    fun getCurrentWeatherApi(): Observable<CurrentWeatherResponse>{

        //val checkNetworkConnection: Boolean = isConnected(context)

        return  apiHelper.getCurrentWeather(
            "Vitebsk",
            API_KEY,
            "metric",
            Locale.getDefault().displayLanguage
        )
    }
    fun upsertCurrentWeather(currentWeather: CurrentWeatherResponse){
        currentWeatherDao.upsertCurrentWeather(currentWeather)
    }
    fun getCurrentWeather(){
        compositeDisposable += Observable.concat(getCurrentWeatherApi(),getCurrentWeatherFromDB())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })

    }
    fun onResponse(response: CurrentWeatherResponse){
        val resultList = response.toString()
    }
    fun onFailure(t: Throwable) {

    }
   /* fun loadCurrentWeather(){
        compositeDisposable += getCurrentWeatherFromDB()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({responseDB ->   })
    }*/
    fun getCurrentWeatherFromDB(): Observable<CurrentWeatherResponse>{
        return currentWeatherDao.getCurrentWeatherDB()
    }
    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        this.add(disposable)
    }
}
