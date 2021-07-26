package com.example.weatherapp.data.repository

import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.weatherapp.data.db.unitlocalized.CurrentWeatherDao
import com.example.weatherapp.data.db.unitlocalized.ForecastWeatherDao
import com.example.weatherapp.data.network.OpenWeatherApiHelper
import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import com.example.weatherapp.data.network.response.ForecastWeatherResponse
import com.example.weatherapp.utils.CheckLocationPremission
import com.example.weatherapp.utils.isLocationEnabled
import com.google.android.gms.location.*
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


const val API_KEY = "69c65f54429caec71cc149fd5a028b38"

class Repository @Inject constructor(
    private val apiHelper: OpenWeatherApiHelper,
    private val currentWeatherDao: CurrentWeatherDao,
    private val forecastWeatherDao: ForecastWeatherDao,
    @ApplicationContext private val appContext:Context
){

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest


    fun getLastLocation(){
        if (ContextCompat.checkSelfPermission(appContext, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (CheckLocationPremission(appContext)) {
                if (isLocationEnabled(appContext)) {
                    fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                        var location: Location? = task.result
                        if (location == null) {
                            NewLocationData()
                        } else {
                            var text: String
                            Log.d("Debug:", "Your Location:" + location.longitude)
                            text =
                                " Long: " + location.longitude + " , Lat: " + location.latitude + "\n" + getCityName(
                                    location.latitude,
                                    location.longitude
                                )
                        }
                    }
                } else {

                }
            } else {
                ActivityResultContracts.RequestPermission()
            }
        }
    }

    private fun getCityName(lat: Double,long: Double):String{
        var cityName:String = ""
        var countryName = ""
        var geoCoder = Geocoder(appContext, Locale.getDefault())
        var Adress = geoCoder.getFromLocation(lat,long,3)

        cityName = Adress.get(0).locality
        countryName = Adress.get(0).countryName
        Log.d("Debug:","Your City: " + cityName + " ; your Country " + countryName)
        return cityName
    }

    fun NewLocationData(){
        var locationRequest =  LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(appContext)
        if (ContextCompat.checkSelfPermission(appContext, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient!!.requestLocationUpdates(
                locationRequest, locationCallback, Looper.myLooper()
            )
        }
    }
    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult) {
            var lastLocation: Location = locationResult.lastLocation
            val text:String
            Log.d("Debug:","your last last location: "+ lastLocation.longitude.toString())
            text = "You Last Location is : Long: "+ lastLocation.longitude + " , Lat: " + lastLocation.latitude + "\n" + getCityName(lastLocation.latitude,lastLocation.longitude)
        }
    }

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

    fun getForecastWeatherApi(): Observable<ForecastWeatherResponse>{

        return  apiHelper.getForecastWeather(
            "Vitebsk",
            API_KEY,
            "metric",
            Locale.getDefault().displayLanguage
        )

    }

    fun upsertCurrentWeather(currentWeather: CurrentWeatherResponse){
        currentWeatherDao.upsertCurrentWeather(currentWeather)
    }

    fun upsertForecastWeather(forecastWeather:ForecastWeatherResponse){
        forecastWeatherDao.upsertForecastWeather(forecastWeather)
    }

    fun getCurrentWeather(){
        compositeDisposable += Observable.concat(getCurrentWeatherApi(),getCurrentWeatherFromDB())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> onCurrentResponse(response) }, { t -> onFailure(t) })

    }

    fun getForecastWeather(){
        compositeDisposable += Observable.concat(getForecastWeatherApi(),getForecastWeatherFromDB())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> onForecastResponse(response) }, { t -> onFailure(t) })

    }

    fun onCurrentResponse(response: CurrentWeatherResponse){
        val resultList = response.toString()
    }

    fun onFailure(t: Throwable) {

    }

    fun onForecastResponse(response: ForecastWeatherResponse){
        val resultList = response.toString()
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

    fun getForecastWeatherFromDB():Observable<ForecastWeatherResponse>{
        return forecastWeatherDao.getForecastWeatherDB()
    }

    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        this.add(disposable)
    }



}
