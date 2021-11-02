package com.example.weatherapp.data.repository

import android.content.Context
import android.location.Location
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.db.entity.current.CurrentWeatherEntity
import com.example.weatherapp.data.db.unitlocalized.CurrentWeatherDao
import com.example.weatherapp.data.network.OpenWeatherApiHelper
import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import com.example.weatherapp.utils.LocationUtils
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
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
    @ApplicationContext private val appContext: Context,
    private val apiHelper: OpenWeatherApiHelper,
    private val currentWeatherDao: CurrentWeatherDao,
    private val gson: Gson,
    private val locationUtils: LocationUtils
){

    private final val TAG:String = javaClass.simpleName


    private val compositeDisposable = CompositeDisposable()


    fun getCurrentWeatherApi(lat:Double,lon:Double): Observable<CurrentWeatherResponse> {
        Log.d(TAG,"gettingFromAPI")

        return  apiHelper.getCurrentWeather(
            lat,
            lon,
            API_KEY,
            "metric",
            Locale.getDefault().displayLanguage)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
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

        Observable.create<Location> {emitter ->
            locationUtils.getLastLocation { location ->
            emitter.onNext(location)
        } } .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {location ->
                getCurrentWeatherApi(location.latitude,location.longitude)
            }
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
        Log.d(TAG,"getting API Error: $t")

    }
    fun getCurrentWeatherFromDB(): Observable<CurrentWeatherEntity>{
        return currentWeatherDao.getCurrentWeatherDB()
    }

    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        this.add(disposable)
    }



}

@BindingAdapter("app:iconId")
fun loadImage (view: ImageView, iconId: String?) {

    if(!iconId.isNullOrEmpty()){
        Glide.with(view.context)
            .load("http://openweathermap.org/img/wn/$iconId@4x.png")
            .centerCrop()
            .placeholder(R.drawable.ic_forecast_cloud_24)
            .into(view)
    }
    else{
        Log.d("Glide","Error: iconID is null")
    }



//    if(iconId != null){
//        Picasso.get()
//            .load("http://openweathermap.org/img/wn/50d.png")
//            .resize(50,50)
//            .centerCrop()
//            .into(view)
//        Picasso.get().isLoggingEnabled = true
//        }
//    else{
//        Log.d("Picasso","Error: iconID is null")
//    }
}

