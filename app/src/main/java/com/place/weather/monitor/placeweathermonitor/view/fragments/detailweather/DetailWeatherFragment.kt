package com.place.weather.monitor.placeweathermonitor.view.fragments.detailweather

import android.content.Context
import com.place.weather.monitor.placeweathermonitor.app.App
import com.place.weather.monitor.placeweathermonitor.databinding.FragmentDetailWeatherBinding
import com.place.weather.monitor.placeweathermonitor.model.base.BaseFragment

class DetailWeatherFragment : BaseFragment<FragmentDetailWeatherBinding>
    (FragmentDetailWeatherBinding::inflate) {
    //region Исходные данные
    companion object {
        fun newInstance() = DetailWeatherFragment().apply { }
    }
    //endregion

    //region Настрока Scope для фрагмента
    override fun onAttach(context: Context) {
        App.instance.appComponent.injectDetailWeatherFragment(this)
        super.onAttach(context)
    }
}