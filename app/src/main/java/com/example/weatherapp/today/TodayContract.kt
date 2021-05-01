package com.example.weatherapp.today

interface TodayContract {
    interface View{
        fun showCurrentWeather(weather:String)
    }
    interface Presenter{
        fun onViewCreated()
    }
}