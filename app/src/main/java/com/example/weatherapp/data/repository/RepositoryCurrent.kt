package com.example.weatherapp.data.repository

import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.db.entity.current.CurrentWeatherEntity
import com.example.weatherapp.data.db.unitlocalized.CurrentWeatherDao
import com.example.weatherapp.data.network.OpenWeatherApiHelper
import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import com.example.weatherapp.utils.LocationUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


const val API_KEY = "69c65f54429caec71cc149fd5a028b38"

class RepositoryCurrent @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val apiHelper: OpenWeatherApiHelper,
    private val currentWeatherDao: CurrentWeatherDao,
    private val locationUtils: LocationUtils
){

    private final val TAG:String = javaClass.simpleName

    val loadedCurrentWeatherDataBase: MutableLiveData<CurrentWeatherEntity> by lazy {
        MutableLiveData<CurrentWeatherEntity>()
    }

    private val compositeDisposable = CompositeDisposable()


    fun getCurrentWeatherApi(lat:Double,lon:Double): Single<CurrentWeatherResponse> =
       apiHelper.getCurrentWeather(
            lat,
            lon,
            API_KEY,
            "metric",
            Locale.getDefault().displayLanguage)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

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
        Single.create<Location> {emitter ->
            locationUtils.getLastLocation { location ->
            emitter.onSuccess(location)
        } } .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {location ->
                getCurrentWeatherApi(location.latitude,location.longitude)
            }
            .subscribe({ response -> onCurrentResponse(response) }, { t -> onFailure(t) })


    }

    fun loadCurrentWeather(){
        getCurrentWeatherFromDB()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({responseDB -> onResponseLoadCurrentWeather(responseDB)},{t -> onFailureLoadCurrentWeather(t)})
    }

    fun onResponseLoadCurrentWeather(response: CurrentWeatherEntity){

        loadedCurrentWeatherDataBase.value = response

        Log.d(TAG,"onLoad $response ")
    }

    fun onFailureLoadCurrentWeather(t: Throwable) {
        Log.d(TAG,"Load exeption: $t")
    }


    fun onCurrentResponse(response: CurrentWeatherResponse){
        Log.d(TAG,"Get From Api Success")
        upsertCurrentWeather(response)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({Log.d(TAG,"Complete upserting")},{ t -> Log.d(TAG,"Upserting did't happend $t")})
    }

    fun onFailure(t: Throwable) {
        Log.d(TAG,"getting API Error: $t")

    }
    fun getCurrentWeatherFromDB(): Observable<CurrentWeatherEntity>{
        return currentWeatherDao.getCurrentWeatherDB()
    }

    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        this.add(disposable)
    }



}


