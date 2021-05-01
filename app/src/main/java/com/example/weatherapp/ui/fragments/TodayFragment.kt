package com.example.weatherapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.today.TodayContract
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_today.*
import javax.inject.Inject

@AndroidEntryPoint
class TodayFragment : Fragment(),TodayContract.View {

    @Inject
    lateinit var presenter: TodayContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        presenter.onViewCreated()
        return inflater.inflate(R.layout.fragment_today, container, false)
    }
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun showCurrentWeather(weather:String) {
     justTextview.setText(weather)
    }
}