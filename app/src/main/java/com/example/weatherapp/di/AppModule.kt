package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.db.ForecastDatabase
import com.example.weatherapp.data.network.OpenWeatherApiHelper
import com.example.weatherapp.data.network.OpenWeatherApiHelperImpl
import com.example.weatherapp.data.network.OpenWeatherApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object AppModule {
@Provides
fun provideOkHttpClient() =if(BuildConfig.DEBUG) {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .build()
}else{
    OkHttpClient
        .Builder()
        .build()
}
@Provides
fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl("https:/api.openweathermap.org/data/2.5/")
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

@Provides
fun provideGson(): Gson = GsonBuilder().create()

@Provides
fun provideOpenWeatherApiService(retrofit: Retrofit):OpenWeatherApiService = retrofit.create(OpenWeatherApiService::class.java)

@Provides
fun provideOpenWeatherApiHelper(openWeatherApiHelper: OpenWeatherApiHelperImpl):OpenWeatherApiHelper = openWeatherApiHelper

@Provides
fun provideForecastDatabase(@ApplicationContext appContext: Context) = ForecastDatabase.getDatabase(appContext)

@Provides
fun provideCurrentWeatherDao(db: ForecastDatabase) = db.currentWeatherDao()


}