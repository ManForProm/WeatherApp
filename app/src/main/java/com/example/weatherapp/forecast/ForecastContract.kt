package com.example.weatherapp.forecast

import com.example.weatherapp.data.recycler.ForecastRecyclerItem

interface ForecastContract {
    interface View{
        fun showGeneratedList(size:Int): List<ForecastRecyclerItem>
    }
    interface Presenter{
        fun onViewCreated()
    }
}