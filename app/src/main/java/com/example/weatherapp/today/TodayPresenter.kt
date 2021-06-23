package com.example.weatherapp.today

import com.example.weatherapp.data.repository.Repository
import javax.inject.Inject


class TodayPresenter @Inject constructor(
    private val view: TodayContract.View,
    private val repository: Repository

):TodayContract.Presenter {

    override fun onViewCreated() {

        repository.getCurrentWeather()
    }

}
