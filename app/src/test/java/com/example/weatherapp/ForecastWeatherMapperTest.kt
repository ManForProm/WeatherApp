package com.example.weatherapp

import com.example.weatherapp.data.db.entity.forecast.ForecastWeatherEntity
import com.example.weatherapp.data.network.response.forecast.City
import com.example.weatherapp.data.network.response.forecast.ListItem
import com.example.weatherapp.data.network.response.forecast.Main
import com.example.weatherapp.data.network.response.forecast.WeatherItem
import com.example.weatherapp.forecast.ForecastWeatherMapper
import org.junit.Assert
import org.junit.Test

class ForecastWeatherMapperTest {

    private val mapper = ForecastWeatherMapper()

    @Test
    fun mapToForecastWeatherViewData(){
        val inputData = ForecastWeatherEntity(
            City(
                 country = "USA",
                 sunrise = 1,
                 timezone = 2,
                 sunset = 3,
                 name = "New York",
                 id = 4,
                 population = 5
            ),
            1,
            "200",
            3,
            listOf(ListItem(dtTxt = "2021-11-30 21:00:00",
                weather = listOf(WeatherItem(icon = "13n",description = "cold")),
                dt = 0,main = Main(temp = 1.11)
                )
            )
        )
        val outputData = mapper.mapToForecastWeatherViewData(inputData)

        Assert.assertEquals(outputData.size, inputData.list?.size)

        outputData.forEach {

            Assert.assertEquals(
                it.iconId, "13n"
            )
            Assert.assertEquals(
                it.time, "11.30 21:00"
            )
            Assert.assertEquals(
                it.weather, "cold"
            )
            Assert.assertEquals(
                it.temp, "1°"
            )
        }

    }



}