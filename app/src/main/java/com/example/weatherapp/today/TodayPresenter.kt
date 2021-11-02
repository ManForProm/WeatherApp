package com.example.weatherapp.today

import android.util.Log
import com.example.weatherapp.data.db.entity.current.CurrentWeatherEntity
import com.example.weatherapp.data.repository.RepositoryCurrent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class TodayPresenter @Inject constructor(
    private val view: TodayContract.View,
    private val repository: RepositoryCurrent

):TodayContract.Presenter {

    private val TAG:String = javaClass.simpleName


    override fun onCreateView(){
        repository.getCurrentWeather()
        loadCurrentWeather()
    }

    override fun onViewCreated() {


    }

    override fun onResume() {
        repository.getCurrentWeather()
        loadCurrentWeather()
    }

    fun loadCurrentWeather(){
        repository.getCurrentWeatherFromDB()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({responseDB -> onResponseLoadCurrentWeather(responseDB)},{t -> onFailureLoadCurrentWeather(t)})
    }

    fun onResponseLoadCurrentWeather(response: CurrentWeatherEntity){
        setCurrentData(response)
        Log.d(TAG,"onLoad $response ")
    }

    fun onFailureLoadCurrentWeather(t: Throwable) {
        Log.d(TAG,"Load exeption: $t")
    }

    fun setCurrentData(response: CurrentWeatherEntity){
        Log.d(TAG,"Setting current data : $response")
        response.apply {  view.showCurrentWeather(name,
            main.temp.toInt().toString(),
            response.weather?.get(0)?.let { it.description },
            main.humidity.toString(),
            "15",
            main.pressure.toString(),
            wind.speed.toInt().toString(),
            setWindOrintation(response.wind.deg),
            response.weather?.get(0)?.let { it.icon }
        )
            Log.d(TAG,"Current data sets")
        }
    }

    fun setWindOrintation(deg:Int):String {
        return when (deg) {
            in 339..360 -> WindOrintation.NORTH.direction
            in 0..23 -> WindOrintation.NORTH.direction
            in 24..68 -> WindOrintation.NE.direction
            in 69..113 -> WindOrintation.EAST.direction
            in 114..158 -> WindOrintation.SE.direction
            in 159..203 -> WindOrintation.SOUTH.direction
            in 204..248 -> WindOrintation.SW.direction
            in 249..293 -> WindOrintation.WEST.direction
            in 293..338 -> WindOrintation.NW.direction
            else -> WindOrintation.NOTHING.direction

        }
    }


}


enum class WindOrintation (var direction:String){
    NORTH("North"),
    EAST("East"),
    WEST("West"),
    SOUTH("South"),
    NW("NW"),
    NE("NE"),
    SW("SW"),
    SE("SE"),
    NOTHING("direction in the hell")
}
