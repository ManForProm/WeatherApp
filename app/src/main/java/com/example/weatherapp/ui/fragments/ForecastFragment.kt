package com.example.weatherapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.adapters.ForecastRecyclerAdapter
import com.example.weatherapp.data.recycler.ForecastRecyclerItem
import com.example.weatherapp.forecast.ForecastContract
import kotlinx.android.synthetic.main.fragment_forecast.*
import javax.inject.Inject

class ForecastFragment @Inject constructor() : Fragment(), ForecastContract.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragment_forecast_recycler_view.adapter = ForecastRecyclerAdapter(showGeneratedList(12))
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun showGeneratedList(size: Int): List<ForecastRecyclerItem> {

    }
}