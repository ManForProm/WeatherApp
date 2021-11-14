package com.example.weatherapp.forecast

import com.example.weatherapp.data.network.response.forecast.ListItem

interface ForecastContract {
    interface View{
       fun setRecyclerViewData(list: List<ListItem>?, city: String)
    }
    interface Presenter{
        fun onViewCreated()
        fun onCreateView()
        fun onScrollRecyclerView()
    }
}