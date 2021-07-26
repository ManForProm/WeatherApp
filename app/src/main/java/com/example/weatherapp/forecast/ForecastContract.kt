package com.example.weatherapp.forecast

interface ForecastContract {
    interface View{
        //fun showGeneratedList(size:Int): List<ForecastRecyclerItem>
    }
    interface Presenter{
        fun onViewCreated()
    }
}