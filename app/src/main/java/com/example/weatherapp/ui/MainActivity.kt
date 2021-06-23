package com.example.weatherapp.ui

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.ui.fragments.ForecastFragment
import com.example.weatherapp.ui.fragments.TodayFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.toolbar.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastFragment = ForecastFragment()
        val todayFragment = TodayFragment()
        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottom_nav)

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
    private fun startGradient(){
        val animationDrawable = toolbar_gradient_layout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(10)
        animationDrawable.setExitFadeDuration(5000)
        animationDrawable.start()
    }
    private fun makeCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
    }
}