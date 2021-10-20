package com.example.weatherapp.today

interface TodayContract {
    interface View{
        fun showCurrentWeather(location: String,
                               tempreture: String,
                               weather: String?,
                               humidity: String ,
//                               precipitation: Int ,
                               pressure: String ,
                               speed: String ,
                               orintation: String
                                  )
    }
    interface Presenter{
        fun onViewCreated()
        fun onCreateView()
    }
}