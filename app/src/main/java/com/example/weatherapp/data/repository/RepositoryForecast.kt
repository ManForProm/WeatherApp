package com.example.weatherapp.data.repository

import com.example.weatherapp.data.db.unitlocalized.ForecastWeatherDao
import com.example.weatherapp.data.network.OpenWeatherApiHelper
import com.example.weatherapp.data.network.response.ForecastWeatherResponse
import com.example.weatherapp.utils.LocationUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


class RepositoryForecast @Inject constructor(
    private val apiHelper: OpenWeatherApiHelper,
    private val forecastWeatherDao: ForecastWeatherDao,
    private val locationUtils: LocationUtils
) {
    private  val compositeDisposable = CompositeDisposable()

    fun getForecastWeatherApi(): Observable<ForecastWeatherResponse> {

        return  apiHelper.getForecastWeather(
            "Vitebsk",
            API_KEY,
            "metric",
            Locale.getDefault().displayLanguage
        )

    }

    fun upsertForecastWeather(forecastWeather:ForecastWeatherResponse){
        forecastWeatherDao.upsertForecastWeather(forecastWeather)
    }

    fun getForecastWeather(){
        compositeDisposable += Observable.concat(getForecastWeatherApi(),getForecastWeatherFromDB())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> onForecastResponse(response) }, { t -> onFailure(t) })

    }

    fun onFailure(t: Throwable) {

    }

    fun onForecastResponse(response: ForecastWeatherResponse){
        val resultList = response.toString()
    }

    fun getForecastWeatherFromDB():Observable<ForecastWeatherResponse>{
        return forecastWeatherDao.getForecastWeatherDB()
    }

    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        this.add(disposable)
    }
}