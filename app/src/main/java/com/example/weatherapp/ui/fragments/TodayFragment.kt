package com.example.weatherapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.data.OpenWeatherApiService
import com.example.weatherapp.data.network.response.CurrentWeatherResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_today.*

class TodayFragment : Fragment() {

    private  val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val apiService = OpenWeatherApiService()

        compositeDisposable += apiService.getCurrentWeather("Vitebsk")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({response -> onResponse(response)},{t -> onFailure(t) })
        return inflater.inflate(R.layout.fragment_today, container, false)
    }
    operator fun CompositeDisposable.plusAssign(disposable: Disposable){
        this.add(disposable)
    }
    private fun onFailure(t: Throwable) {
        justTextview.setText(t.message)
    }
    private fun onResponse(response: CurrentWeatherResponse){
        justTextview.setText(response.toString())
    }
    override fun onDestroy() {
        super.onDestroy()
        //compositeDisposable.dispose()
    }
}