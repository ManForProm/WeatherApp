package com.example.weatherapp.ui

import android.content.pm.PackageManager
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.ui.fragments.ForecastFragment
import com.example.weatherapp.ui.fragments.TodayFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.toolbar.*


@AndroidEntryPoint
class MainActivity() : AppCompatActivity() {
    var PERMISSION_ID = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastFragment = ForecastFragment()
        val todayFragment = TodayFragment()
        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottom_nav)

        ActivityResultContracts.RequestPermission()

        RequestPermission()

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