package com.example.weatherapp.today

import android.content.Intent
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import com.example.weatherapp.base.BasePresenter
import com.example.weatherapp.data.db.entity.current.CurrentWeatherEntity
import com.example.weatherapp.data.repository.RepositoryCurrent
import com.example.weatherapp.ui.fragments.TodayFragment
import javax.inject.Inject


class TodayPresenter @Inject constructor(
    private val view: TodayContract.View,
    private val repository: RepositoryCurrent

):TodayContract.Presenter,BasePresenter<TodayFragment>() {

    private val TAG:String = javaClass.simpleName

    private lateinit var currentWeatherResponse: CurrentWeatherEntity

    val currentWeatherObserver = Observer<CurrentWeatherEntity>{ CurrentWeatherEntity ->
        currentWeatherResponse = CurrentWeatherEntity
        setCurrentData(CurrentWeatherEntity)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        Log.d(TAG,"Getting current data ")
        getLoadCurrentWeather()
    }

    override fun onViewAttach(view: TodayFragment, viewLifecycle: Lifecycle) {
        attachView(view,viewLifecycle)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        view()?.viewLifecycleOwner?.let { repository.loadedCurrentWeatherDataBase.observe(it,
            currentWeatherObserver) }
    }

    override fun onClickShare() {
        view.shareCurrentData(shareCurrentDataPresenter(currentWeatherResponse))
    }



    fun setCurrentData(response: CurrentWeatherEntity){
        Log.d(TAG,"Setting current data : $response")
        response.apply {  view.showCurrentWeather(name,
            main.temp.toInt().toString(),
            weather?.get(0)?.description,
            main.humidity.toString(),
            "15",
            main.pressure.toString(),
            wind.speed.toInt().toString(),
            setWindOrintation(wind.deg),
            weather?.get(0)?.icon
        )
            Log.d(TAG,"Current data sets")
        }
    }

    fun getLoadCurrentWeather(){
        repository.getCurrentWeather()
        repository.loadCurrentWeather()
    }

    fun shareCurrentDataPresenter(response: CurrentWeatherEntity):Intent{
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT
                ,"Location:${response.name} \n" +
                        "Tempreture:${response.main.temp.toInt()} \n" +
                        "Weather:${response.weather?.get(0)?.description} \n" +
                        "Humidity:${response.main.humidity} \n" +
                        "Speed:${response.wind.speed} \n" +
                        "Pressure:${response.main.pressure} \n" +
                        "Wind orintation:${setWindOrintation(response.wind.deg)} \n")
            type = "text/plain"
        }
        return sendIntent
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


enum class WindOrintation(var direction:String){
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
