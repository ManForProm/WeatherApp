package com.example.weatherapp.data.repository

import android.location.Location
import android.util.Log
import com.example.weatherapp.data.db.entity.forecast.ForecastWeatherEntity
import com.example.weatherapp.data.db.unitlocalized.ForecastWeatherDao
import com.example.weatherapp.data.network.OpenWeatherApiHelper
import com.example.weatherapp.data.network.response.ForecastWeatherResponse
import com.example.weatherapp.utils.LocationUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


class RepositoryForecast @Inject constructor(
    private val apiHelper: OpenWeatherApiHelper,
    private val forecastWeatherDao: ForecastWeatherDao,
    private val locationUtils: LocationUtils
) {
    private final val TAG:String = javaClass.simpleName

    private  val compositeDisposable = CompositeDisposable()

    fun getForecastWeatherApi(lat:Double, lon:Double): Observable<ForecastWeatherResponse> =
    apiHelper.getForecastWeather(
            lat,
            lon,
            API_KEY,
            "metric",
            Locale.getDefault().displayLanguage)



    fun upsertForecastWeather(forecastWeather:ForecastWeatherResponse):Completable{

        val forecastWeatherEntity = ForecastWeatherEntity(
            city = forecastWeather.city,
            cnt = forecastWeather.cnt,
            cod = forecastWeather.cod,
            message = forecastWeather.message,
            list = forecastWeather.list

        )
       return forecastWeatherDao.upsertForecastWeather(forecastWeatherEntity)
    }

    fun getForecastWeather(){

        Observable.create<Location> { emitter ->
            locationUtils.getLastLocation { location ->
                emitter.onNext(location)
            } } .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {location ->
                getForecastWeatherApi(location.latitude,location.longitude)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            .subscribe({ response -> onForecastResponse(response) }, { t -> onFailure(t) })


    }

    fun onFailure(t: Throwable) {

    }

    fun onForecastResponse(response: ForecastWeatherResponse){
        Log.d(TAG,"Get From Api Success")
        upsertForecastWeather(response)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ Log.d(TAG,"Complete upserting")},{ t -> Log.d(TAG,"Upserting did't happend $t")})
    }

    fun getForecastWeatherFromDB():Observable<ForecastWeatherEntity>{
        return forecastWeatherDao.getForecastWeatherDB()
    }
}
