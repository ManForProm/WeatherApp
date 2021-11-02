package com.example.weatherapp.utils

import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

class LocationUtils @Inject constructor(@ApplicationContext private val appContext: Context)  {
    private  var fusedLocationProviderClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(appContext)
     var locationRequest: LocationRequest? = null

//    fun getLastLocation():Observable<Location>{
//        return Observable.create { emitter ->
//
//            if (ContextCompat.checkSelfPermission(
//                    appContext,
//                    android.Manifest.permission.ACCESS_FINE_LOCATION
//                ) == PackageManager.PERMISSION_GRANTED
//            ) {
//                if (CheckLocationPremission(appContext)) {
//                    if (isLocationEnabled(appContext)) {
//                         val listener = fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
//                            var location: Location? = task.result
//                            if (location == null) {
//                                NewLocationData()
//                            } else {
//                                Log.d(
//                                    "Debug:", "Your Location:" + location.longitude + " " + "," +
//                                            " Lat: " + location.latitude + "\n" + getCityName(
//                                        location.latitude,
//                                        location.longitude
//                                    )
//                                )
//                                emitter.onNext(location)
//                            }
//
//                        }
//                        emitter.setCancellable { listener.isCanceled  }
//                    } else {
//
//                    }
//                } else {
//                    ActivityResultContracts.RequestPermission()
//                }
//            }
//        emitter.onComplete()
//        }
//    }
fun getLastLocation(callback:(Location) -> Unit) {
    if (ContextCompat.checkSelfPermission(
            appContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        if (CheckLocationPremission(appContext)) {
            if (isLocationEnabled(appContext)) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        NewLocationData()
                    } else {
                        Log.d(
                            "Debug:", "Your Location:" + location.longitude + " " + "," +
                                    " Lat: " + location.latitude + "\n" + getCityName(
                                location.latitude,
                                location.longitude
                            )
                        )
                        callback(location)

                    }
                }

            }
        } else {
            ActivityResultContracts.RequestPermission()
        }
    }
}

     fun getCityName(lat: Double,long: Double):String{
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
        var locationRequest: LocationRequest? = null
        locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest?.interval = 0
        locationRequest?.fastestInterval = 0
        locationRequest?.numUpdates = 1
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(appContext)
        if (ContextCompat.checkSelfPermission(appContext, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient!!.requestLocationUpdates(
                locationRequest, locationCallback, Looper.myLooper()
            )
        }
    }
     val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult) {
            var lastLocation: Location = locationResult.lastLocation
            val text:String
            Log.d("Debug:","your last last location: "+ lastLocation.longitude.toString())
            text = "You Last Location is : Long: "+ lastLocation.longitude + " , Lat: " + lastLocation.latitude + "\n" + getCityName(lastLocation.latitude,lastLocation.longitude)
        }
    }
}