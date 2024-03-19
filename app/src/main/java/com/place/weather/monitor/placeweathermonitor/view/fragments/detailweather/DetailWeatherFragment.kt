package com.place.weather.monitor.placeweathermonitor.view.fragments.detailweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.place.weather.monitor.placeweathermonitor.R

class DetailWeatherFragment : Fragment() {
    //region Исходные данные
    companion object {
        fun newInstance() = DetailWeatherFragment().apply { }
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_weather, container, false)
    }
}