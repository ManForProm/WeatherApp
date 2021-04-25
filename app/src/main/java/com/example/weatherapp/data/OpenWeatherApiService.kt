package com.example.weatherapp.data

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//http://api.openweathermap.org/data/2.5/weather?q=London&appid=704ff0f1c1c358807a94555e07da85f2&lang=en

const val API_KEY = "704ff0f1c1c358807a94555e07da85f2"

interface OpenWeatherApiService {

    @GET("weather")
    fun getCurrentWeather(
        @Query("q") location:String,
        @Query("appid") key:String = API_KEY,
        @Query("units") units:String = "metric",
        @Query("lang") language:String = "en"
    ):Observable<CurrentWeatherResponse>
     companion object {
       operator fun invoke():OpenWeatherApiService{
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
            val client = OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https:/api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(OpenWeatherApiService::class.java);
        }
    }
}