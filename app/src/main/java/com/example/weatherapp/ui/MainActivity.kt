package com.example.weatherapp.ui

import android.content.pm.PackageManager
import android.graphics.drawable.AnimationDrawable
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.ui.fragments.ForecastFragment
import com.example.weatherapp.ui.fragments.TodayFragment
import com.example.weatherapp.utils.CheckLocationPremission
import com.example.weatherapp.utils.isLocationEnabled
import com.google.android.gms.location.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_today.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var PERMISSION_ID = 10

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastFragment = ForecastFragment()
        val todayFragment = TodayFragment()
        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottom_nav)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        ActivityResultContracts.RequestPermission()

        RequestPermission()
        getLastLocation()

        makeCurrentFragment(todayFragment)

        startGradient()

        setSupportActionBar(findViewById(R.id.toolbar))

        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_today_sunny -> {makeCurrentFragment(todayFragment)
                toolbar_text.setText("Today")}
                R.id.ic_forecast_cloud -> {makeCurrentFragment(forecastFragment)
                toolbar_text.setText("Forecast")}
            }
            true
        }

    }

    fun RequestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )

    }

    fun getLastLocation(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (CheckLocationPremission(this)) {
                if (isLocationEnabled(this)) {
                    fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                        var location: Location? = task.result
                        if (location == null) {
                            Log.d("Debug:", "Your Location:null")
                            NewLocationData()
                        } else {
                            Log.d("Debug:", "Your Location:" + location.longitude)
                            fragment_today_location.text =
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
        var geoCoder = Geocoder(this, Locale.getDefault())
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
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_ID){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("Debug", "You have permission")
            }
        }
    }
    private fun makeCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
    }

    private fun startGradient(){
        val animationDrawable = toolbar_gradient_layout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(10)
        animationDrawable.setExitFadeDuration(5000)
        animationDrawable.start()
    }

}