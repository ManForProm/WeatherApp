package com.example.weatherapp.today

import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import com.example.weatherapp.data.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class TodayPresenter @Inject constructor(
    private val view: TodayContract.View,
    private val repository: Repository

):TodayContract.Presenter {

    private  val compositeDisposable = CompositeDisposable()

    override fun onViewCreated() {
        getCurrentWeather()
    }
    fun getCurrentWeather(){
        compositeDisposable += repository.getCurrentWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response -> onResponse(response)},{t-> onFailure(t)})
    }
    private fun onResponse(response: CurrentWeatherResponse){
        view.showCurrentWeather(response.toString())
    }
    private fun onFailure(t: Throwable) {
       view.showCurrentWeather(t.toString())
    }
    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        this.add(disposable)
    }
}
