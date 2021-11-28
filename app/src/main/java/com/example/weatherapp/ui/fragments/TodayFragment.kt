package com.example.weatherapp.ui.fragments

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentTodayBinding
import com.example.weatherapp.today.TodayContract
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class TodayFragment @Inject constructor() : Fragment(),TodayContract.View {

    @Inject
    lateinit var presenter: TodayContract.Presenter
    lateinit var binding: FragmentTodayBinding

    private var sharedPreferences:SharedPreferences? = null



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        presenter.onViewAttach(this, getLifecycle())

        sharedPreferences = context?.getSharedPreferences("com.example.weatherapp",
            Context.MODE_PRIVATE)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodayBinding.inflate(layoutInflater)

        binding.shareButton.setOnClickListener {
            presenter.onClickShare()
        }

        if(sharedPreferences?.getBoolean("first_run",true) == true){
            presenter.onFirstLaunch()

            sharedPreferences?.edit()?.putBoolean("first_run",false)?.apply()
        }

        return binding.root
    }

    override fun shareCurrentData(intent: Intent) {
            startActivity(intent)
    }

    override fun showFirstLaunchInternetConnectionAttention(onErrorInternetConnection: Boolean) {
        val alertDialogBuilder:MaterialAlertDialogBuilder
        context?.let {
            alertDialogBuilder = MaterialAlertDialogBuilder(it,R.style.ThemeOverlay_App_MaterialAlertDialog)
            alertDialogBuilder.apply {
                setTitle("Internet Connection Alert")
                setIcon(R.drawable.ic_error)
                setMessage("Internet connection is required for the first launch")
                background = ColorDrawable(
                    Color.parseColor("#FEFEFA")
                )
                setPositiveButton("Ok"){dialog,which ->
                    sharedPreferences?.edit()?.putBoolean("first_run",true)?.apply()
                    activity?.finish()
                }
                setOnKeyListener(DialogInterface.OnKeyListener { arg0, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        sharedPreferences?.edit()?.putBoolean("first_run",true)?.apply()
                        activity?.finish()
                    }
                    true
                })



            }
            val dialog = alertDialogBuilder.create()
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }





    }

    override fun showInternetConnectionAttention(onErrorInternetConnection:Boolean) {
        binding.visibility = onErrorInternetConnection
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