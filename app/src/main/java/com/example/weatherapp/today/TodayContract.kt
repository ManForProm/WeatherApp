package com.example.weatherapp.today

import android.content.Intent
import androidx.lifecycle.Lifecycle
import com.example.weatherapp.ui.fragments.TodayFragment

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
        fun showInternetConnectionAttention(onErrorInternetConnection: Boolean)
        fun showFirstLaunchInternetConnectionAttention(onErrorInternetConnection: Boolean)
        fun shareCurrentData(intent: Intent)
    }
    interface Presenter{
        fun onClickShare()
        fun onFirstLaunch()
        fun onViewAttach(view: TodayFragment, viewLifecycle: Lifecycle)
    }
}