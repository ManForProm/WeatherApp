package com.example.weatherapp.forecast

import com.example.weatherapp.data.repository.RepositoryForecast
import com.example.weatherapp.today.TodayContract
import javax.inject.Inject

class ForecastPresenter @Inject constructor(
    private val view: TodayContract.View,
    private val repository: RepositoryForecast
):ForecastContract.Presenter{
    override fun onViewCreated() {
        //repository.getForecastWeather()
    }

}