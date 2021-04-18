package com.example.weatherapp.ui.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.ui.fragments.ForecastFragment
import com.example.weatherapp.ui.fragments.TodayFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastFragment = ForecastFragment()
        val todayFragment = TodayFragment()
        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottom_nav)
        makeCurrentFragment(todayFragment)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_today_sunny -> makeCurrentFragment(todayFragment)
                R.id.ic_forecast_cloud -> makeCurrentFragment(forecastFragment)
            }
            true
        }

    }
    private fun makeCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
    }
}