package com.example.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.recycler.ForecastRecyclerItem
import kotlinx.android.synthetic.main.forecast_recycler_item.view.*

class ForecastRecyclerAdapter(private val forecastRecyclerList: List<ForecastRecyclerItem>): RecyclerView.Adapter<ForecastRecyclerAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.forecast_recycler_item,
            parent, false)


        return ForecastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {

        val currentItem = forecastRecyclerList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.temp.text = currentItem.temp
        holder.time.text = currentItem.time
        holder.weather.text = currentItem.weather

    }

    override fun getItemCount() = forecastRecyclerList.size

    class ForecastViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.forecast_recycler_image_view
        val weather: TextView =  itemView.forecast_recycler_weather_text_view
        val time: TextView = itemView.forecast_recycler_time_text_view
        val temp: TextView = itemView.forecast_temp_text_view

    }
}