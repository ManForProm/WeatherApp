package com.example.weatherapp.utils

import android.content.Context
import android.location.LocationManager

fun isLocationEnabled(context: Context):Boolean{
    var locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
}