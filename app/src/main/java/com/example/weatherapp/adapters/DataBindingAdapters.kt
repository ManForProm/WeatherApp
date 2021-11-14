package com.example.weatherapp.adapters

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.weatherapp.R

@BindingAdapter("layoutMarginBottom")
fun setLayoutMarginBottom(view: View, dimen: Float) {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.bottomMargin = dimen.toInt()
    view.layoutParams = layoutParams
}

@ExperimentalStdlibApi
@BindingAdapter("app:windOrintation")
fun setWindOrintationImage (view: ImageView, direction: String?) {
    view.apply {
        when (direction) {

            "North" -> setImageResource(R.drawable.ic_forecast_orintation.rotateLeft(10))
            "East" -> setImageResource(R.drawable.ic_forecast_orintation.rotateLeft(20))
            "West" -> setImageResource(R.drawable.ic_forecast_orintation.rotateLeft(30))
            "South" -> setImageResource(R.drawable.ic_forecast_orintation.rotateLeft(40))
            "NW" -> setImageResource(R.drawable.ic_forecast_orintation.rotateLeft(50))
            "NE" -> setImageResource(R.drawable.ic_forecast_orintation.rotateLeft(60))
            "SW" -> setImageResource(R.drawable.ic_forecast_orintation.rotateLeft(70))
            "SE" -> setImageResource(R.drawable.ic_forecast_orintation.rotateLeft(80))
            "direction in the hell" -> setImageResource(R.drawable.ic_forecast_orintation)


        }
    }
}

@BindingAdapter("app:iconId")
fun loadImage (view: ImageView, iconId: String?) {

    if (!iconId.isNullOrEmpty()) {
        Glide.with(view.context)
            .load("http://openweathermap.org/img/wn/$iconId@4x.png")
            .centerCrop()
            .placeholder(R.drawable.ic_forecast_cloud_24)
            .into(view)
    } else {
        Log.d("Glide", "Error: iconID is null")
    }

}

//    if(iconId != null){
//        Picasso.get()
//            .load("http://openweathermap.org/img/wn/50d.png")
//            .resize(50,50)
//            .centerCrop()
//            .into(view)
//        Picasso.get().isLoggingEnabled = true
//        }
//    else{
//        Log.d("Picasso","Error: iconID is null")
//    }
