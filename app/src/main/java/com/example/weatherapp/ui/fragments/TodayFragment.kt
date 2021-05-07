package com.example.weatherapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.databinding.FragmentTodayBinding
import com.example.weatherapp.today.TodayContract
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TodayFragment @Inject constructor() : Fragment(),TodayContract.View {

    @Inject
    lateinit var presenter: TodayContract.Presenter
    lateinit var binding: FragmentTodayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodayBinding.inflate(layoutInflater)
        presenter.onViewCreated()
        return binding.root
    }
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun showCurrentWeather(weather:String) {
     binding.justTextview.text = weather
    }
}