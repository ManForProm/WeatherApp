package com.example.weatherapp.data.db.unitlocalized

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.network.response.CURRENT_WEATHER_ID
import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import io.reactivex.rxjava3.core.Observable

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertCurrentWeather(currentWeather: CurrentWeatherResponse)

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertCurrentWeather(currentWeather: Weather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertCurrentMain(currentMain: Main)*/

    @Query("SELECT * FROM current_weather WHERE id_current_weather = $CURRENT_WEATHER_ID")
    fun getCurrentWeatherDB():Observable<CurrentWeatherResponse>

   /* @Query("SELECT * FROM main WHERE id_main = $MAIN_ID")
    fun getCurrentMainDB():Observable<ArrayList<Main>>

    @Query("SELECT * FROM weather WHERE id_weather = $WEATHER_ID")
    fun getWeatherDB(): Observable<ArrayList<Weather>>*/

}