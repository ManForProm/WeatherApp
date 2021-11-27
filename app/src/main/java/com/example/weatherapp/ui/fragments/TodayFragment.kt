package com.example.weatherapp.ui.fragments

import android.content.Intent
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

        presenter.onViewAttach(this, getLifecycle())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTodayBinding.inflate(layoutInflater)

        binding.shareButton.setOnClickListener {
            presenter.onClickShare()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()


    }

    override fun shareCurrentData(intent: Intent) {
            startActivity(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textToday = text

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
                                     precipitation: String ,
                                     pressure: String ,
                                     speed: String ,
                                     orintation: String,
                                     iconId: String?
    ) {

        binding.currentWeather = CurrentWeatherDataClass(location = location,
            tempreture = tempreture,
            weather = weather,
            humidity = humidity,
            precipitation = precipitation,
            pressure = pressure,
            speed = speed,
            orintation = orintation,
            iconId = iconId
        )
    }
}

    data class CurrentWeatherDataClass(
            val location: String = "",
            val tempreture: String = "",
            val weather: String? = "",
            val humidity: String = "",
            val precipitation: String = "",
            val pressure: String = "",
            val speed: String = "",
            val orintation: String = "",
            val iconId: String? = ""
    ){}