package com.example.weatherapp.today

import android.content.Intent

interface TodayContract {
    interface View{
        fun showCurrentWeather(location: String,
                               tempreture: String,
                               weather: String?,
                               humidity: String ,
                               precipitation: String ,
                               pressure: String ,
                               speed: String ,
                               orintation: String,
                               iconId:String?
        )
        fun shareCurrentData(intent: Intent)
    }
    interface Presenter{
        fun onViewCreated()
        fun onClickShare()
        fun onResume()
    }
}