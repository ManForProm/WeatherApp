package com.example.weatherapp.forecast

import android.util.Log
import com.example.weatherapp.data.db.entity.forecast.ForecastWeatherEntity
import com.example.weatherapp.data.repository.RepositoryForecast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ForecastPresenter @Inject constructor(
    private val view: ForecastContract.View,
    private val repository: RepositoryForecast
):ForecastContract.Presenter{

    private final val TAG:String = javaClass.simpleName

    override fun onViewCreated() {
    }

    override fun onCreateView() {
        repository.getForecastWeather()
        loadForecastWeather()
    }

    override fun onScrollRecyclerView() {

    }

    fun loadForecastWeather(){
        repository.getForecastWeatherFromDB()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({responseDB -> onResponseLoadCurrentWeather(responseDB)},{t -> onFailureLoadCurrentWeather(t)})
    }
    fun onResponseLoadCurrentWeather(response: ForecastWeatherEntity){
        view.setRecyclerViewData(response.list, response.city.name)
        Log.d(TAG,"onLoad ${response.list?.get(0)} ")
    }

    fun onFailureLoadCurrentWeather(t: Throwable) {
        Log.d(TAG,"Load exeption: $t")
    }

}