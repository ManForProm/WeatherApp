package com.example.weatherapp.data.db.unitlocalized

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.db.entity.current.CurrentWeatherEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

const val CURRENT_WEATHER_ID = 0

@Dao
interface CurrentWeatherDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun upsertCurrentWeather(currentWeather: CurrentWeatherEntity):Completable

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun upsertCurrentWeather(currentWeather: Weather)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun upsertCurrentMain(currentMain: Main)

    @Query("SELECT * FROM current_weather_table WHERE id_current_weather = $CURRENT_WEATHER_ID")
    fun getCurrentWeatherDB():Observable<CurrentWeatherEntity>

   /* @Query("SELECT * FROM main WHERE id_main = $MAIN_ID")
    fun getCurrentMainDB():Observable<ArrayList<Main>>

    @Query("SELECT * FROM weather WHERE id_weather = $WEATHER_ID")
    fun getWeatherDB(): Observable<ArrayList<Weather>>*/

}