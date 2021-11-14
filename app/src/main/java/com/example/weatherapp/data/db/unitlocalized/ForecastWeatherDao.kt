package com.example.weatherapp.data.db.unitlocalized

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.db.entity.forecast.ForecastWeatherEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

const val FORECAST_WEATHER_ID = 0

@Dao
interface ForecastWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun upsertForecastWeather(forecastWeather: ForecastWeatherEntity):Completable

    @Query("SELECT * FROM forecast_weather WHERE id_forecast_weather = $FORECAST_WEATHER_ID")
    fun getForecastWeatherDB(): Observable<ForecastWeatherEntity>
}