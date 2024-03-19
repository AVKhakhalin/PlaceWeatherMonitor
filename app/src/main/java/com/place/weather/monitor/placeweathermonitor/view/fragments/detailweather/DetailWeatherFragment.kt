package com.place.weather.monitor.placeweathermonitor.view.fragments.detailweather

import com.place.weather.monitor.placeweathermonitor.databinding.FragmentDetailWeatherBinding
import com.place.weather.monitor.placeweathermonitor.view.model.base.BaseFragment

class DetailWeatherFragment : BaseFragment<FragmentDetailWeatherBinding>
    (FragmentDetailWeatherBinding::inflate) {
    //region Исходные данные
    companion object {
        fun newInstance() = DetailWeatherFragment().apply { }
    }
    //endregion
}