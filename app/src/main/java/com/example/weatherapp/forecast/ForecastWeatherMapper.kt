package com.example.weatherapp.forecast

import com.example.weatherapp.data.db.entity.forecast.ForecastWeatherEntity

class ForecastWeatherMapper {

    fun mapToForecastWeatherViewData(response: ForecastWeatherEntity):List<ForecastViewData> {
        return mutableListOf<ForecastViewData>().apply {

            response.list?.forEach {
                it.apply {
                    add(
                        ForecastViewData(
                            iconId = weather?.get(0)?.icon,
                            time = dtTxt.removeRange(0, 5).dropLast(3).replace("-", "."),
                            weather = weather?.get(0)?.description,
                            temp = main.temp.toInt().toString().plus("°")
                        )
                    )
                }
            }


        }

    }


}