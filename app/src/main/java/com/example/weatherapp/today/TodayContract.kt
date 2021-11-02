package com.example.weatherapp.today

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
    }
    interface Presenter{
        fun onViewCreated()
        fun onCreateView()
        fun onResume()
    }
}