package com.example.weatherapp.di

import androidx.fragment.app.Fragment
import com.example.weatherapp.forecast.ForecastContract
import com.example.weatherapp.forecast.ForecastPresenter
import com.example.weatherapp.ui.fragments.ForecastFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
abstract class ForecastModule{
    @Binds
    abstract fun bindForecastFragment(forecastFragment: ForecastFragment): ForecastContract.View

    @Binds
    abstract fun bindForecastPresenter(forecastPresenter: ForecastPresenter): ForecastContract.Presenter
}
@InstallIn(FragmentComponent::class)
@Module
object ForecastFragmentModule {

    @Provides fun bindForecastFragment(fragment: Fragment):ForecastFragment{

        return fragment as ForecastFragment

    }

}