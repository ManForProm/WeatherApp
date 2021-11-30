    package com.example.weatherapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.recycler.ForecastRecyclerViewAdapter
import com.example.weatherapp.databinding.FragmentForecastBinding
import com.example.weatherapp.forecast.ForecastContract
import com.example.weatherapp.forecast.ForecastViewData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ForecastFragment @Inject constructor() : Fragment(), ForecastContract.View {

    @Inject
    lateinit var presenter: ForecastContract.Presenter
    lateinit var binding: FragmentForecastBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val manager: RecyclerView.LayoutManager = LinearLayoutManager(activity)

        presenter.onCreateView()

        binding = FragmentForecastBinding.inflate(layoutInflater)

        binding.fragmentForecastRecyclerView.layoutManager = manager

        return binding.root
    }

    //Try to create padding onScroll
//    fun addRecyclerViewOnScrollListener(){
//
//        binding.fragmentForecastRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//
//                recyclerView.marginBottom
//                if (!recyclerView.canScrollVertically(1)) {
//                    presenter.onScrollRecyclerView()
//                }
//            }
//
//        })
//    }

    override fun setRecyclerViewData(list: List<ForecastViewData>, city:String) {

       // val listFake:List<ListItem>? = listOf(ListItem(dtTxt = "aa",weather = listOf(WeatherItem(icon = "13n",description = "cold")),dt = 0,main = Main(temp = 1.11)))

        val adapter = ForecastRecyclerViewAdapter(list, city)

        binding.fragmentForecastRecyclerView.adapter = adapter

    }
    //Create Conext menu for Recycler View
//    override fun onCreateContextMenu(
//        menu: ContextMenu,
//        v: View,
//        menuInfo: ContextMenu.ContextMenuInfo?
//    ) {
//        super.onCreateContextMenu(menu, v, menuInfo)
//
//    }
}