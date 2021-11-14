    package com.example.weatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.data.db.entity.converters.*
import com.example.weatherapp.data.db.entity.current.CurrentWeatherEntity
import com.example.weatherapp.data.db.entity.forecast.ForecastWeatherEntity
import com.example.weatherapp.data.db.unitlocalized.CurrentWeatherDao
import com.example.weatherapp.data.db.unitlocalized.ForecastWeatherDao

    @Database(
    entities = [ ForecastWeatherEntity::class,CurrentWeatherEntity::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(WeatherConverter::class, MainConverter::class, WindConverter::class,
    CloudsConverter::class,SysConverter::class,CoordConverter::class, CityConverter::class,ListItemConverter::class)
abstract class ForecastDatabase: RoomDatabase(){
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun forecastWeatherDao(): ForecastWeatherDao


    companion object{
        @Volatile private var instace: ForecastDatabase? = null
        private val LOCK = Any()

         fun getDatabase(context: Context)= instace ?: synchronized(LOCK){
            instace ?: buildDatabase(context).also { instace = it }
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ForecastDatabase::class.java, "forecast.db")
                .fallbackToDestructiveMigration()
                .build()
    }

}