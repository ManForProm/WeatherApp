package com.example.weatherapp.di

import androidx.fragment.app.Fragment
import com.example.weatherapp.today.TodayContract
import com.example.weatherapp.today.TodayPresenter
import com.example.weatherapp.ui.fragments.TodayFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class TodayModule{
    @Binds
    abstract fun bindTodayFragment(todayFragment: TodayFragment): TodayContract.View

    @Binds
    abstract fun bindTodayPresenter(todayPresenter: TodayPresenter): TodayContract.Presenter
}


@InstallIn(ActivityComponent::class)
@Module
object TodayFragmentModule {

@Provides
fun bindTodayFragment(fragment:Fragment):TodayFragment{
    return fragment as TodayFragment
}

}