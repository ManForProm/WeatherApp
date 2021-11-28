package com.example.weatherapp.forecast

interface ForecastContract {
    interface View{
       fun setRecyclerViewData(list: List<ForecastViewData>, city: String)
    }
    interface Presenter{
        fun onViewCreated()
        fun onCreateView()
        fun onScrollRecyclerView()
    }
}