package com.example.weatherapp.data.db.unitlocalized

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.db.entity.MAIN_ID
import com.example.weatherapp.data.db.entity.Main
import com.example.weatherapp.data.network.response.CurrentWeatherResponse

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertCurrentWeather(currentWeatherEntry: CurrentWeatherResponse)
    @Query("SELECT * FROM current_weather WHERE id = $MAIN_ID")
    fun getCurrentWeatherDB(): LiveData<List<Main>>

}