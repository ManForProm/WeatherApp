package com.example.weatherapp.data.repository

import android.util.Log
import com.example.weatherapp.data.db.entity.current.CurrentWeatherEntity
import com.example.weatherapp.data.db.unitlocalized.CurrentWeatherDao
import com.example.weatherapp.data.network.OpenWeatherApiHelper
import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import com.example.weatherapp.utils.LocationUtils
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


const val API_KEY = "69c65f54429caec71cc149fd5a028b38"

class RepositoryCurrent @Inject constructor(
    private val apiHelper: OpenWeatherApiHelper,
    private val currentWeatherDao: CurrentWeatherDao,
    private val gson: Gson,
    private val locationUtils: LocationUtils
){

    private final val TAG:String = javaClass.simpleName


    private val compositeDisposable = CompositeDisposable()

    var onLoadCurrentWeather = "null"

    fun getCurrentWeatherApi(): Observable<CurrentWeatherResponse> {

        Log.d(TAG,"gettingFromAPI")
        //val checkNetworkConnection: Boolean = isConnected(context)

        return  apiHelper.getCurrentWeather(
            "Vitebsk",
            API_KEY,
            "metric",
            Locale.getDefault().displayLanguage)
    }

    fun upsertCurrentWeather(currentWeather: CurrentWeatherResponse):Completable{
        Log.d(TAG,"upserting $currentWeather")
        val currentWeatherEntity = CurrentWeatherEntity(
            visibility = currentWeather.visibility,
            timezone = currentWeather.timezone,
            main = currentWeather.main,
            clouds = currentWeather.clouds,
            sys = currentWeather.sys,
            dt = currentWeather.dt,
            weather = currentWeather.weather,
            name = currentWeather.name,
            cod = currentWeather.cod,
            id = currentWeather.id,
            base = currentWeather.base,
            wind = currentWeather.wind

        )
        return currentWeatherDao.upsertCurrentWeather(currentWeatherEntity)
    }

    fun getCurrentWeather(){
        getCurrentWeatherApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> onCurrentResponse(response) }, { t -> onFailure(t) })

    }

    fun onCurrentResponse(response: CurrentWeatherResponse){
        Log.d(TAG,"Get From Api Success")
        upsertCurrentWeather(response)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({Log.d(TAG,"Complete upserting")},{ t -> Log.d(TAG,"Upserting did't happend $t")})
    }

    fun onFailure(t: Throwable) {

    }
    fun getCurrentWeatherFromDB(): Observable<CurrentWeatherEntity>{
        return currentWeatherDao.getCurrentWeatherDB()
    }

    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        this.add(disposable)
    }



}
