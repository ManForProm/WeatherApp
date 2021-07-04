package com.example.weatherapp.data.db.unitlocalized

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.network.response.FORECAST_WEATHER_ID
import com.example.weatherapp.data.network.response.ForecastWeatherResponse
import io.reactivex.rxjava3.core.Observable

@Dao
interface ForecastWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertForecastWeather(forecastWeather: ForecastWeatherResponse)

    @Query("SELECT * FROM forecast_weather WHERE id_forecast_weather = $FORECAST_WEATHER_ID")
    fun getForecastWeatherDB(): Observable<ForecastWeatherResponse>
}