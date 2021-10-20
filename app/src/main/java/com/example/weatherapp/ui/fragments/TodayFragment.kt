package com.example.weatherapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentTodayBinding
import com.example.weatherapp.today.TodayContract
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TodayFragment @Inject constructor() : Fragment(),TodayContract.View {

    @Inject
    lateinit var presenter: TodayContract.Presenter
    lateinit var binding: FragmentTodayBinding

    private var text:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.onCreateView()

        binding = FragmentTodayBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onViewCreated()

        val tv: TextView = view.findViewById(R.id.fragment_today_location_text)
        tv.setText(text)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun showCurrentWeather( location: String,
                                     tempreture: String,
                                     weather: String?,
                                     humidity: String ,
//                                     precipitation: Int ,
                                     pressure: String ,
                                     speed: String ,
                                     orintation: String
    ) {

        binding.currentWeather = CurrentWeatherDataClass(location = location
            , tempreture = tempreture
            , weather = weather
            , humidity = humidity,
//        precipitation = precipitation
             pressure = pressure, speed = speed, orintation = orintation)
    }
}

    data class CurrentWeatherDataClass(
            val location: String = "",
            val tempreture: String = "",
            val weather: String? = "",
            val humidity: String = "",
//            val precipitation: Int = 0,
            val pressure: String = "",
            val speed: String = "",
            val orintation: String = ""
    ){}