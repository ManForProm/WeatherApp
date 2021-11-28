package com.example.weatherapp.data.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ForecastRecyclerHeaderBinding
import com.example.weatherapp.databinding.ForecastRecyclerItemBinding
import com.example.weatherapp.forecast.ForecastViewData

const val TYPE_HEADER = 0
const val TYPE_WEATHER = 1

class ForecastRecyclerViewAdapter(private val list: List<ForecastViewData>?, private val city: String): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG:String = javaClass.simpleName

    inner class WeatherViewHolder(private val binding: ForecastRecyclerItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(list: ForecastViewData?){
                binding.iconId = list?.iconId
                binding.forecastRecyclerTimeTextView.setText(list?.time)
                binding.forecastRecyclerWeatherTextView.setText(list?.weather)
                binding.forecastTempTextView.setText(list?.temp)

        }

    }

    inner class HeaderViewHolder(private val binding: ForecastRecyclerHeaderBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(city: String){

            binding.forecastRecyclerCityTextView.text = city

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        return when(viewType){
            TYPE_HEADER -> HeaderViewHolder(ForecastRecyclerHeaderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
            TYPE_WEATHER -> WeatherViewHolder(ForecastRecyclerItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            0 -> TYPE_HEADER
            else -> TYPE_WEATHER
        }
    }

    override fun getItemCount(): Int {
        return list!!.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HeaderViewHolder -> holder.bind(city)
            is WeatherViewHolder -> holder.bind(list?.get(position - 1))
        }
    }


}